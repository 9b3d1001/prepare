package multithreading;

import java.time.LocalDateTime;

public class Thread1 extends Thread {

    private final long millisToSleep;

    public Thread1(String name, long millisToSleep) {
        super(name);
        this.millisToSleep = millisToSleep;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Extended Thread Name: " + threadName);
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
