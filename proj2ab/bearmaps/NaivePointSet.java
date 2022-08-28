package bearmaps;

import java.util.*;

public class NaivePointSet implements PointSet{

    private Point[] points;

    public NaivePointSet(List<Point> points) {
        this.points = (Point[]) points.toArray(points.toArray(new Point[points.size()]));
    }
    @Override
    public Point nearest(double x, double y) {

        Point min_point = points[0];
        Point target = new Point(x, y);
        double min = Point.distance(target, points[0]);
        for (int i = 0; i < points.length; i++) {
            double distance = Point.distance(target, points[i]);
            if (distance < min) {
                min_point = points[i];
                min = distance;
            }
        }
        return min_point;
    }
}
