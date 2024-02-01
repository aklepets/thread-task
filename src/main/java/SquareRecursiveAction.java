import java.util.concurrent.RecursiveAction;

public class SquareRecursiveAction extends RecursiveAction {

    final double[] array;
    final int start;
    final int end;
    public static final int THRESHOLD = 100_000;

    public SquareRecursiveAction(double[] array, int start, int end){
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end - start < THRESHOLD) {
            for(int i = start; i < end; i++){
                array[i] = Math.sqrt(array[i]);
            }
        } else {
            int middle = (start + end)/2;
            invokeAll(new SquareRecursiveAction(array, start, end), new SquareRecursiveAction(array, middle, end));
        }
    }
}
