package multithreading;

public class WaitNotifyProducerConsumer {


    private int value;

    public synchronized void produce() throws InterruptedException {
        while(value > 5) {
            wait();
        }
        value = value + 1;
        System.out.println(Thread.currentThread().getName() + "-" + value);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while(value == 0) {
            wait();
        }
        value = value - 1;
        System.out.println(Thread.currentThread().getName() + "-" + value);
        notify();
    }

    public static void main(String[] args) {
        WaitNotifyProducerConsumer waitNotifyProducerConsumer = new WaitNotifyProducerConsumer();
        Thread pt = new Thread(() -> {
           try {
               for (int i = 1; i <= 5; i++) {
                   waitNotifyProducerConsumer.produce();
                   Thread.sleep(1000);
               }
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
        }, "producer");

        Thread ct = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    waitNotifyProducerConsumer.consume();
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "consumer");
        pt.start();
        ct.start();
        try {
            pt.join();
            ct.join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Value at the end " + waitNotifyProducerConsumer.value);
    }
}
