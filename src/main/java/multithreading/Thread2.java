package multithreading;

import java.time.LocalDateTime;

public class Thread2 implements Runnable {

    private final long millisToSleep;

    public Thread2(long millisToSleep) {
        this.millisToSleep = millisToSleep;
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Runnable Thread Name: " + threadName);
        for(int i = 1; i <= 10;  i++) {
            System.out.println(threadName + "-" + i + "-" + LocalDateTime.now());
            try {
                Thread.sleep(millisToSleep);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
