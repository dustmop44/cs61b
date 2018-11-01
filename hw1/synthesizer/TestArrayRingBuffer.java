package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void name() {
        System.out.println((Math.random()-.5));
    }

    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        assertEquals(arb.peek(), 3);
        assertEquals(arb.capacity(), 10);
        arb.fillcount();
        arb.dequeue();
        assertEquals(arb.dequeue(),4);
        assertEquals(arb.peek(), 5);

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
