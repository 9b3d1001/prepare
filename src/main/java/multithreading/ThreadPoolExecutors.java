package multithreading;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutors {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " running task");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };


        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            for(int i = 1; i <=5; i++) {
                executorService.submit(runnable);
            }
            System.out.println("Main is here after submit");
            List<Callable<String>> callables = new ArrayList<>();
            for(int i = 1; i <=5; i++) {
                callables.add(getCallableTask(i));
            }
            try {
                executorService.invokeAny(callables);
                System.out.println("Main is here after invoke any");


                executorService.invokeAll(callables);
                System.out.println("Main is here after invoke all");
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Callable<String> getCallableTask(int threadNumber) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + "-" + threadNumber + "-running task");
            Thread.sleep(1000L * threadNumber);
            System.out.println(Thread.currentThread().getName() + "-" + threadNumber + "-task finished");
            return Thread.currentThread().getName()  + threadNumber;
        };
    }
}
