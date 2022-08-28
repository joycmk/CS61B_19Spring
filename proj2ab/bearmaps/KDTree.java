package bearmaps;

import java.security.PrivateKey;
import java.util.*;

public class KDTree implements PointSet{

    private Node root;

    private class Node {

        private Point point;
        private Node left;
        private Node right;

        private int compare_factor; //0: X, 1:Y
        private int size;

        public Node (Point p) {
            point = p;
            size = 1;
        }

        public Node () {

        }
        public int getCompare_factor() {
            return compare_factor;
        }

        public Point getPoint() {
            return point;
        }

        public void setCompare_factor(int compare_factor) {
            this.compare_factor = compare_factor;
        }

        public double dist (Node x) {
            return Point.distance(point,x.getPoint());
        }

        private double compare (Node n) {
            //negative means n is bigger
            if (compare_factor == 0) {
                return point.getX() - n.getPoint().getX();
            } else {
                return point.getY() - n.getPoint().getY();
            }
        }


    }

    public KDTree (List<Point> points) {

        for (int i = 0; i < points.size() ; i++) {
                add (points.get(i));
        }

    }

    public void add (Point p) {
        root = add(root,p,0);
    }

    private Node add (Node origin,Point p, int compare_factor) {
        if (origin == null) {
            Node new_node = new Node(p);
            new_node.setCompare_factor(compare_factor);
            return new_node;
        }

        Point compared_point = origin.getPoint();

        if (compared_point.getX() == p.getX() && compared_point.getY() == p.getY()) {
            Node left_node = origin.left;
            Node right_node = origin.right;
            origin = new Node(p);
            origin.setCompare_factor(compare_factor);
            origin.left = left_node;
            origin.right = right_node;
            origin.size = size(origin.left) +size(origin.right) + 1;
            return origin;
        }

        if (compare_factor == 0) {
            //compare x
            if (compared_point.getX() < p.getX()) {
                //go right
                origin.right = add(origin.right,p,1);
            } else {
                origin.left = add(origin.left,p,1);
            }
        } else if (compare_factor == 1) {
            if (compared_point.getY() < p.getY()) {
                origin.right = add(origin.right,p,0);
            } else {
                origin.left = add(origin.left,p,0);
            }
        }

        origin.size = size(origin.left) +size(origin.right) + 1;
        return origin;
    }

    public int size() {
        return size(root);
    }
    private int size(Node p) {
        if (p == null) {
            return 0;
        } else {
            return p.size;
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x,y);
        Node goal_node = new Node(goal);
        Node best = nearest(root,goal_node,root);
        return best.getPoint();
    }

    private Node nearest (Node n, Node goal_node, Node best) {
        if (n == null) {
            return best;
        }

        if (n.dist(goal_node) < best.dist(goal_node)) {
            best = n;
        }

        Node good_side = new Node();
        Node bad_side = new Node();

        if (n.compare(goal_node) < 0) {
            good_side = n.right;
            bad_side = n.left;
        } else {
            good_side = n.left;
            bad_side = n.right;
        }

        best = nearest(good_side,goal_node,best);

        if (bad_side != null && area_min(goal_node,bad_side) < best.dist(goal_node)) {
            best = nearest(bad_side,goal_node,best);
        }
        return best;
    }

    private double area_min (Node goal_node, Node n) {
        if (n.getCompare_factor() == 0) {
            return Math.abs(goal_node.getPoint().getX() - n.getPoint().getX());
        } else {
            return Math.abs(goal_node.getPoint().getY() - n.getPoint().getY());
        }
    }
}
