import java.util.concurrent.ForkJoinPool;

public class Runner {
    public static void main(String[] args){
        final int SIZE = 500_000_000;
        double[] numArray = new double[SIZE];

//        Fill the array with random doubles
        for(int i = 0; i < SIZE; i++){
            numArray[i] = Math.random();
        }

//        Measure the time of square root transformation using Fork/Join framework
        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new SquareRecursiveAction(numArray, 0, SIZE));

        long midTime = System.currentTimeMillis();
        System.out.printf("Transformation by Fork/Join took %d milliseconds%n", midTime - startTime);

//        Do same transformation using linear calculation (for loop)
        double sum = 0;
        for(double v : numArray) {
            sum += v*v;
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("Linear transformation took %d milliseconds%n", endTime - midTime);
    }
}
