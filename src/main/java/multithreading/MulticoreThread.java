package multithreading;

public class MulticoreThread {

    public static void main(String[] args) {
        int numCores = Runtime.getRuntime().availableProcessors(); // 8, for example

        for (int i = 0; i < numCores; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    double x = Math.sqrt(Math.random()); // CPU-bound work
                    System.out.println(Thread.currentThread().getName() + " - " + x);
                }
            });
            t.start();
        }
    }
}
