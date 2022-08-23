import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Приложението на този клас ще е да завърши всички нишки, когато вече няма ордъри за правене.
//С него не разчитаме на време относно кога нишките ще спрат, а ще спрат когато вече няма никакви поръчки в процес.
public class Inspector
    implements Runnable
{
    private ExecutorService executorCooks = Executors.newCachedThreadPool();
    private ExecutorService executorFurnaces = Executors.newCachedThreadPool();
    private ArrayList<Order> ordersInProcess = new ArrayList<>();

    void setExecutors(ExecutorService executorFurnaces, ExecutorService executorCooks)
    {
        this.executorCooks = executorCooks;
        this.executorFurnaces = executorFurnaces;
    }

    void setOrdersInProcess(ArrayList<Order> inProcess)
    {
        this.ordersInProcess = inProcess;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                synchronized (ordersInProcess)
                {
                    if(ordersInProcess.size() == 0)
                    {
                        break;
                    }
                }
                Thread.sleep(500); //Слийпа е, за да може да проверява през малко време, а не да проверя моментално отново.
            }

            executorFurnaces.shutdownNow();
            executorCooks.shutdownNow();
        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}