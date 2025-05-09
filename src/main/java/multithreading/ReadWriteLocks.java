package multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLocks {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private int count = 1;

    public int read(int threadNumber) throws InterruptedException {
            if (readLock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println("Reading the count " + Thread.currentThread().getName() + "=" + threadNumber + "-" + count +
                        "-" +
                        LocalDateTime.now());
                    Thread.sleep(2000);
                    return count;
                } finally {
                    readLock.unlock();
                }
            } else {
                System.out.println("Could not acquire read lock " + Thread.currentThread().getName() + "=" + threadNumber + "-" + LocalDateTime.now());
            }


        return 0;
    }

    public void write(int threadNumber) throws InterruptedException {
            if(writeLock.tryLock()) {
                try {
                    System.out.println("Writing the count " + Thread.currentThread().getName() + "-" + threadNumber+ "-" +
                        LocalDateTime.now());
                    Thread.sleep(5000);
                    count++;
                    System.out.println("Wrote the count " + Thread.currentThread().getName() + "-" + threadNumber + "-" + count + "-" +
                        LocalDateTime.now());
                } finally {
                    writeLock.unlock();
                }
            } else {
                System.out.println("Could not acquire write lock " + Thread.currentThread().getName() + "=" + threadNumber + "-" + LocalDateTime.now());
            }

    }

    public static void main(String[] args) {
        ReadWriteLocks readWriteLocks = new ReadWriteLocks();
        Thread thread21 = new Thread(new ReadWriteLockThread(1000, readWriteLocks), "threadRR.1");
        Thread thread22 = new Thread(new ReadWriteLockThread(2000, readWriteLocks), "threadRR.2");
        thread21.start();
        thread22.start();
        try {
            thread21.join();
            thread22.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final Count is " + readWriteLocks.count);
    }
}
