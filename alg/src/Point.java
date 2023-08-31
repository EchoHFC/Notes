import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {

        if ((this.x == that.x) && (this.y == that.y)) { return Double.NEGATIVE_INFINITY; }
        if (this.y == that.y) { return +0.0; }
        if (this.x == that.x) { return Double.POSITIVE_INFINITY; }
        return 1.0 *  (that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) {

        if (this.y < that.y) { return -1; }
        else if (this.y > that.y) { return +1; }
        else return Integer.compare(this.x, that.x);
    }



    public Comparator<Point> slopeOrder() {
        return new SlopeComparator();
    }

    private class SlopeComparator implements Comparator<Point> {

        public int compare(Point a, Point b) {

            return Double.compare(slopeTo(a), slopeTo(b));

        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {

    }
}
