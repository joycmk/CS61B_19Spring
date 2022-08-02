package es.datastructur.synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer x = new ArrayRingBuffer(3);
        x.enqueue(33.1); // 33.1 null null

        x.enqueue(44.8); // 33.1 44.8 null
        x.dequeue(); //33.1

        x.enqueue(62.3); // 44.8 62.3  null
        x.enqueue(-3.4); // 44.8 62.3 -3.4
        x.dequeue(); //44.8
        x.enqueue(8.3); // 62.3 -3.4 8.3  null
        x.enqueue(7.4); // Exception()
    }
}
