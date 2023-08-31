import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
public class PointSET {
    private SET<Point2D> points;
    public PointSET() {
        points = new SET<Point2D>();
    }
    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The argument is null");
        }

        points.add(p);
    }
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("The argument is null");
        }
        Stack<Point2D> p = new Stack<Point2D>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                p.push(point);
            }
        }
        return p;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The argument is null");
        }
        if (isEmpty()) {
            return null;
        }
        double dis = Double.POSITIVE_INFINITY;
        Point2D ps = new Point2D(0.0, 0.0);
        for (Point2D point : points) {
            if (dis > point.distanceSquaredTo(p)) {
                dis = point.distanceSquaredTo(p);
                ps = point;
            }
        }
    return ps;
    }

    public static void main(String[] args) {

    }
}
