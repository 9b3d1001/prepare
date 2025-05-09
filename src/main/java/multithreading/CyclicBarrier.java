package multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrier {

    public static void main(String[] args) {
        System.out.println("Main is making three workers and doing its work-"+ LocalDateTime.now());
        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(4, () -> {
            System.out.println("The barrier is reached-" + LocalDateTime.now());
        });

        for(int i = 1; i <= 3; i++) {
            int sleepTime = i * 1000;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " working-" + LocalDateTime.now());
                try {
                    Thread.sleep(sleepTime);
                    System.out.println(Thread.currentThread().getName() + " finishes-" + LocalDateTime.now());
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Worker" + i).start();
        }

        try {
            cyclicBarrier.await();
            System.out.println("Work finished-"+ LocalDateTime.now());
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
