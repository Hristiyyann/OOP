import java.util.ArrayList;

public class Furnace
    implements Runnable
{
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Order> ordersForBaking = new ArrayList<>();
    private Order order = null;
    private static int numberForFurnace = 1;
    private int furnaceNumber;

    public Furnace()
    {
        this.furnaceNumber = numberForFurnace;
        numberForFurnace++;
    }

    public void setRestaurantAttributes(Restaurant restaurant)
    {
        this.orders = restaurant.getOrders();
        this.ordersForBaking = restaurant.getOrdersForBaking();
    }

    public int indexForLastBaked()
    {
        int flag = 0, index = 0;

        synchronized (orders)
        {
            if(orders.size() != 0)
            {
                for (Order orderForSearch : orders)
                {
                    if(!orderForSearch.isBaked())
                    {
                        flag = 1;
                        index =  orders.indexOf(orderForSearch);
                    }
                }
            }

            if(flag != 1)
            {
                index = orders.size();
            }
        }

        return index;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println("Furnace " + furnaceNumber + " started");

            while(true)
            {
                order = null;
                synchronized (ordersForBaking)
                {
                    if (ordersForBaking.size() != 0)
                    {
                        order = ordersForBaking.get(0); //Ако има поръчки за печене винаги се взима най-напред сложената.
                        ordersForBaking.remove(order);
                        System.out.println("Furnace " + furnaceNumber + " is taking order " + order.getOrderNumber());
                    }

                    if(order == null)
                    {
                        System.out.println("Furnace " + furnaceNumber + " is waiting...");
                        ordersForBaking.wait(); // Ако няма никакви поръчки за печене, фурната ще изчака за нова поръчка за печене.
                        continue;
                    }
                }

                System.out.println("Furnace " + furnaceNumber + " is baking " + order.getOrderNumber());
                Thread.sleep(2500);
                order.setBaked(true);
                System.out.println("Furnace " + furnaceNumber + " stop baking " + order.getOrderNumber());

                synchronized (orders)
                {
                    orders.add(indexForLastBaked(), order);
                    //Тук добавяме вече изпечената поръчка към масива, от който готвачите взимат поръчки
                    //Идеята на indexForLastBaked е да ни каже къде е последната изпечена поръчка и да добавим новоизпечената
                    //непосредствено след нея
                    //Добавят се преди новите поръчки, защото изпечените са с приоритет
                    order = null;
                    orders.notify();
                }
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Furnace " + furnaceNumber + " is exiting!");
        }
    }
}
