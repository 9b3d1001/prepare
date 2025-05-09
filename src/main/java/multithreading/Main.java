package multithreading;

public class Main {

    public static void main(String[] args) {
        Thread thread11 = new Thread1("thread1.1", 1000);
        Thread thread12 = new Thread1("thread1.2", 2000);
        String mainThread = Thread.currentThread().getName();
        System.out.println(mainThread + "is now starting extended threads ");
        thread11.start();
        thread12.start();
        try {
            thread11.join();
            thread12.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(mainThread + "is now starting runnable threads ");
        Thread thread21 = new Thread(new Thread2(1000), "thread2.1");
        Thread thread22 = new Thread(new Thread2(2000), "thread2.2");
        thread21.start();
        thread22.start();
        try {
            thread21.join();
            thread22.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(mainThread + " is ending");

    }
}
