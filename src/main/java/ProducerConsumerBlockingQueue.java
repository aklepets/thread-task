import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueue {

    static class ProducerBlockingQueue implements Runnable{
        private final BlockingQueue<Integer> sharedQueue;

        public ProducerBlockingQueue(BlockingQueue<Integer> sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        @Override
        public void run() {
            for(int i = 1; i<=10; i++) {
                try{
                    System.out.println("Produced: " + i);
                    sharedQueue.put(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class ConsumerBlockingQueue implements Runnable {
        private final BlockingQueue<Integer> sharedQueue;

        public ConsumerBlockingQueue(BlockingQueue<Integer> sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    int item = sharedQueue.take();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);

        Thread producerThread = new Thread(new ProducerBlockingQueue(sharedQueue));
        Thread consumerThread = new Thread(new ConsumerBlockingQueue(sharedQueue));

        producerThread.start();
        consumerThread.start();
    }
}
