import edu.princeton.cs.algs4.Queue;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Double> example1 = new Queue<>();
        example1.enqueue(5.0);
        example1.enqueue(1.2);
        example1.enqueue(3.4);
        example1.enqueue(8.7);
        example1.enqueue(7.8);
        example1.enqueue(-2.3);

        assertTrue(isSorted(QuickSort.quickSort(example1)));

        Queue<Integer> example2 = new Queue<>();
        example2.enqueue(5);
        example2.enqueue(1);
        example2.enqueue(3);
        example2.enqueue(8);
        example2.enqueue(7);

        assertTrue(isSorted(QuickSort.quickSort(example2)));

    }
    @Test
    public void testMergeSort() {
        Queue<Double> example1 = new Queue<>();
        example1.enqueue(5.0);
        example1.enqueue(1.2);
        example1.enqueue(3.4);
        example1.enqueue(8.7);
        example1.enqueue(7.8);
        example1.enqueue(-2.3);

        assertTrue(isSorted(MergeSort.mergeSort(example1)));

        Queue<Integer> example2 = new Queue<>();
        example2.enqueue(5);
        example2.enqueue(1);
        example2.enqueue(3);
        example2.enqueue(8);
        example2.enqueue(7);

        assertTrue(isSorted(MergeSort.mergeSort(example2)));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
