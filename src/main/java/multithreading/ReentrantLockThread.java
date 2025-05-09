package multithreading;

import java.time.LocalDateTime;

public class ReentrantLockThread implements Runnable {

    private final long millisToSleep;

    private final RenetrantLocks renetrantLocks;

    public ReentrantLockThread(long millisToSleep, RenetrantLocks renetrantLocks) {
        this.millisToSleep = millisToSleep;
        this.renetrantLocks = renetrantLocks;
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("ReentrantLockThread Name: " + threadName);
        for(int i = 1; i <= 11;  i++) {
            try {
                renetrantLocks.incrementCounter(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
