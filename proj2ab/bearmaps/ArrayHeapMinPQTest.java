package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    @Test
    public static void test1() {
        ArrayHeapMinPQ<Integer> example = new ArrayHeapMinPQ<>();
        example.add(5,5);
        example.add(8,8);
        example.add(1,1);
        example.add(10,10);
        example.add(4,4);
        example.removeSmallest();
        int target = example.getSmallest();
        assertEquals(4,target);
    }

    @Test
    public static void test2() {
        ArrayHeapMinPQ<String> example = new ArrayHeapMinPQ<>();
        example.add("grandfather",68);
        example.add("grandmother",64);
        example.add("mother",38);
        example.add("father",40);
        example.add("aunt",35);
        example.add("uncle",43);
        example.add("me",18);

        String target1 = example.getSmallest();
        assertEquals("me",target1);

        example.changePriority("me",70);
        String target2 = example.getSmallest();
        assertEquals("aunt",target2);

        example.removeSmallest();

        String target3 = example.getSmallest();
        assertEquals("mother",target3);

        example.changePriority("me",18);
        String target4 = example.getSmallest();
        assertEquals("me",target4);


    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
