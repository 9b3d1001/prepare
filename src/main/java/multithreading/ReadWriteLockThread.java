package multithreading;

public class ReadWriteLockThread implements Runnable {

    private final long millisToSleep;

    private final ReadWriteLocks readWriteLocks;

    public ReadWriteLockThread(long millisToSleep, ReadWriteLocks readWriteLocks) {
        this.millisToSleep = millisToSleep;
        this.readWriteLocks = readWriteLocks;
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("ReadWriteLockThread Name: " + threadName);
        for(int i = 1; i <= 11;  i++) {
            try {
                if(i % 5 == 0) {
                    readWriteLocks.write(i);
                } else {
                    readWriteLocks.read(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
