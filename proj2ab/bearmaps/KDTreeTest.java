package bearmaps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public static void test_add() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);

        KDTree kd = new KDTree(List.of(p1,p2,p3,p4,p5,p6));

    }

    @Test
    public static void test_nearest() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4,4);

        KDTree kd = new KDTree(List.of(p1,p2,p3,p4,p5,p6,p7));
        Point best = kd.nearest(0,7);

        assertEquals(1, best.getX(),0);
        assertEquals(5,best.getY(),0);
    }

    public static void main(String[] args) {
        test_add();
        test_nearest();
    }
}
