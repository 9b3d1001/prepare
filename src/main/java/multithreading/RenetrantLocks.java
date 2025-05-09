package multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RenetrantLocks {

    private final ReentrantLock reentrantLock = new ReentrantLock(true);

    private int count;

    public void incrementCounter(int threadNumber) throws InterruptedException {
        try {
            if (reentrantLock.tryLock(10, TimeUnit.SECONDS)) {
                System.out.println("Lock Acquired By " + Thread.currentThread().getName() + "-" + threadNumber + "-" + LocalDateTime.now());
                count++;
                Thread.sleep(threadNumber * 1000L);
            } else {
                System.out.println("Could not acquire lock " + Thread.currentThread().getName() + "-" + threadNumber + "-" + LocalDateTime.now());
            }
        } finally {
            if(reentrantLock.isHeldByCurrentThread()) {
                reentrantLock.unlock();
            }
        }
    }

    public int getCounter() {
        return count;
    }

    public static void main(String[] args) {
        RenetrantLocks renetrantLocks = new RenetrantLocks();
        Thread thread21 = new Thread(new ReentrantLockThread(1000, renetrantLocks), "threadR.1");
        Thread thread22 = new Thread(new ReentrantLockThread(2000, renetrantLocks), "threadR.2");
        thread21.start();
        thread22.start();
        try {
            thread21.join();
            thread22.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final Count is " + renetrantLocks.getCounter());
    }
}
