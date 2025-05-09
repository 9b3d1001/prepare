package multithreading;

import java.time.LocalDateTime;

public class CountDownLatch {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " is waiting for workers-" + LocalDateTime.now());
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(3);
        for(int i = 1; i <= 3; i++) {
            int sleepTime = i * 1000;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " working-" + LocalDateTime.now());
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " finishes-" + LocalDateTime.now());
                countDownLatch.countDown();
            }, "Worker" + i).start();
        }

        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + " has received the work from workers-"  + LocalDateTime.now());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
