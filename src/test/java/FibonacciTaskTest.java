import org.junit.Test;
import java.util.concurrent.ForkJoinPool;
import static org.junit.Assert.assertEquals;

public class FibonacciTaskTest {
    @Test
    public void testFibonacciTest(){
        assertEquals(1134903170L, new ForkJoinPool().invoke(new FibonacciTask(45)).longValue());
    }
}
