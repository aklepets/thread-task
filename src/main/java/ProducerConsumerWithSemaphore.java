import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerWithSemaphore {
    static Semaphore semProducer = new Semaphore(1);
    static Semaphore semConsumer = new Semaphore(0);
    static Queue<Integer> queue = new LinkedList<>();

    static class Producer extends Thread {
        public void run() {
            for (int i=1; i<=500_000; i++) {
                try {
                    semProducer.acquire();
                    queue.add(i);
                    System.out.println("Produced: " + i);
                    semConsumer.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            for(int i=1; i<=500_000; i++){
                try {
                    semConsumer.acquire();
                    System.out.println("Consumed: " + queue.poll());
                    semProducer.release();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }
}
