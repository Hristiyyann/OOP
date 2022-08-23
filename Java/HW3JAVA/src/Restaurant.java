import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant
{
   private ArrayList<Cook> cooks = new ArrayList<>();
   private ArrayList<Order> orders = new ArrayList<>();
   private ArrayList<Order> ordersForBaking = new ArrayList<>();
   private ArrayList<Order> ordersInProcess = new ArrayList<>();
   private ExecutorService executorCooks = Executors.newCachedThreadPool();
   private ExecutorService executorFurnaces = Executors.newCachedThreadPool();
   static ArrayList<String> validBreads, validMeat, validVegetables, validSauces;
   private boolean isRestaurantClosed = false;

   static
   {
      validBreads = new ArrayList<>
              (Arrays.asList("White bread", "Wheat bread", "Rue bread", "Grain bread", "Italian bread"));
      validMeat = new ArrayList<>
              (Arrays.asList("Chicken", "Veal", "Beef", "Tuna", "Ham"));
      validVegetables = new ArrayList<>
              (Arrays.asList("Tomato", "Cucumber", "Iceberg", "Onion", "Pepper", "Olive", "Pickles"));
      validSauces = new ArrayList<>
              (Arrays.asList("Mayo", "Ketchup", "Mustard", "BBQ", "Ranch"));
   }

   public void addOrder(Order order)
   {
      if(!isRestaurantClosed)
      {
         if(order.isValid())
         {
            synchronized (orders)
            {
               orders.add(order);
               System.out.println("Order " + order.getOrderNumber() + " have been added!");
               orders.notify();
            }
         }
         else
         {
            System.out.println("Order is not valid!");
         }
      }
      else
      {
         System.out.println("Restaurant is already closed! You can't place your order " + order.getOrderNumber()  + "!");
      }
   }

   public void addCook(Cook cook)
   {
      cook.setRestaurantAttributes(this);
      executorCooks.submit(new Thread(cook));
      cooks.add(cook);
   }

   public void addFurnace(Furnace furnace)
   {
      furnace.setRestaurantAttributes(this);
      executorFurnaces.submit(new Thread(furnace));
   }

   public void finalizeOrder(Order order)
   {
      System.out.println("Order " + order.getOrderNumber() + " is finalized and ready for taking!");
   }

   public void shutdown()
   {
      System.out.println("\nRestaurant closes soon!\n");
      isRestaurantClosed = true;

      ExecutorService executorInspector = Executors.newFixedThreadPool(1);
      Inspector inspector = new Inspector();
      inspector.setExecutors(executorFurnaces, executorCooks);
      inspector.setOrdersInProcess(ordersInProcess);
      executorInspector.submit(inspector);
      executorInspector.shutdown();
   }

   public ArrayList<Order> getOrders() {return orders;}
   public ArrayList<Order> getOrdersForBaking() {return ordersForBaking;}
   public ArrayList<Order> getOrdersInProcess() {return ordersInProcess;}
   public boolean isRestaurantClosed() {return isRestaurantClosed;}
}