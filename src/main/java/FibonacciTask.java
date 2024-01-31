import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Long> {
    private static final long THRESHOLD = 10;
    private final long number;

    public FibonacciTask(long number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        if (number <= THRESHOLD) {
            return fibonacci(number);
        } else {
            FibonacciTask worker1 = new FibonacciTask(number - 1);
            FibonacciTask worker2 = new FibonacciTask(number - 2);
            worker1.fork();

            return worker2.compute()+worker1.join();
        }
    }

    private Long fibonacci(long n) {
        if (n <=1 ) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }
}
