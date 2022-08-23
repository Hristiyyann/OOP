import java.util.ArrayList;

public class Cook
    implements Runnable
{
    private String cookName;
    private Restaurant restaurant;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Order> ordersForBaking = new ArrayList<>();
    private ArrayList<Order> ordersInProcess = new ArrayList<>();

    public Cook(String cookName)
    {
        this.cookName = cookName;
    }
    public void log(String msg)
    {
        System.out.println("[Cook <" + cookName + ">] <" + msg + ">");
    }

    public void setRestaurantAttributes(Restaurant restaurant)
    {
        this.restaurant = restaurant;
        this.ordersForBaking = restaurant.getOrdersForBaking();
        this.orders = restaurant.getOrders();
        this.ordersInProcess = restaurant.getOrdersInProcess();
    }

    public void puttingIngredientsFromIndexToIndex(int fromIndex, int toIndex, Order order)
    {
        try
        {
            for(int i = fromIndex; i < toIndex; i++)
            {
                String ingredient = order.getIngredients().get(i);
                if(ingredient != null)
                {
                    Thread.sleep(1200);
                    log("put " + ingredient + " on order " + order.getOrderNumber());
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void startOrder(Order order)
    {
        try
        {
            log("takes order " + order.getOrderNumber());
            Thread.sleep(300);
            puttingIngredientsFromIndexToIndex(0, 3, order);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if(order.getRequirements().contains("no bake"))
        {
            endOrder(order); // Ако има "no bake" директно преминаваме към endOrder, без да печем нищо.
            return;
        }

        synchronized (ordersForBaking)
        {
            //Ако пък поръчката няма ограничение да се пече, тя се добавя към опашката от чакащи поръчки за печене
            ordersForBaking.add(order);
            ordersForBaking.notify();
            log("added order " + order.getOrderNumber() + " to the furnaces queue");
        }
    }

    public void endOrder(Order order)
    {
        puttingIngredientsFromIndexToIndex(3, 9, order);
        synchronized (ordersInProcess)
        {
            //Когато поръчката е готова, ще я махнем от inProcess
            ordersInProcess.remove(order);
        }
        restaurant.finalizeOrder(order);
    }

    @Override
    public void run()
    {
        log("starts working");
        boolean bakedOrder, newOrder, isCookShutdown = false;
        try
        {
            while(true)
            {
                Order order = null;
                bakedOrder = newOrder = false;

                if(restaurant.isRestaurantClosed() && !isCookShutdown)
                {
                    //Информираме, че когато ресторанта затваря готвачите няма да започват нови поръчки, а само да довършват вече започнати.
                    log("can only take baked orders from now");
                    isCookShutdown = true;
                }

                synchronized (orders)
                {
                    for(Order orderForCheck : orders)
                    {
                        if(orderForCheck.isBaked())
                        {
                            bakedOrder = true;
                            order = orderForCheck;
                            break;
                        }

                        else
                        {
                            if(!isCookShutdown) // Ще вземем нова поръчка само ако ресторанта не затваря
                            {
                                newOrder = true;
                                order = orderForCheck;
                                break;
                            }
                        }
                    }

                    if(newOrder || bakedOrder)
                    {
                        // Ако готвач е взел някаква поръчка ще я махне от поръчките, които чакат
                        orders.remove(order);
                        if(newOrder) // Ако пък е нова поръчка, а не изпечена, ще се добави към inProcess
                        {
                            synchronized (ordersInProcess)
                            {
                                ordersInProcess.add(order);
                            }
                        }
                    }

                    else
                    {
                        log("is waiting for order");
                        orders.wait();
                        log("has received order");
                    }
                }

                if(newOrder) {startOrder(order);} // Ако започваме нова поръчка
                else if(bakedOrder) {endOrder(order);} // Ако продължаваме изпечена поръчка

            }
        }
        catch (InterruptedException e)
        {
            log("is exiting...");
        }
    }
}
