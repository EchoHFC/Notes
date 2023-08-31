import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private LineSegment[] temp;
    private int sizeNow;
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            pointsCopy[i] = points[i];
        }
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        Arrays.sort(pointsCopy);
        temp = new LineSegment[8];;
        for (int i = 0; i < pointsCopy.length-3; i++) {
            Point p = pointsCopy[i];
            Point[] other = new Point[pointsCopy.length-i-1];
            for (int j = i+1; j < pointsCopy.length; j++) {
                other[j -i - 1] = pointsCopy[j];
            }
            Comparator<Point> slope = p.slopeOrder();
            Arrays.sort(other, slope);

            for (int k = 0; k < other.length-2; k++) {
                if (Double.compare(p.slopeTo(other[k]), p.slopeTo(other[k+2]))==0) {
                    int index = k + 2;
                    while ((index < other.length - 1) && (Double.compare(p.slopeTo(other[index]), p.slopeTo(other[index + 1]))==0)) {
                        index++;
                    }
                    if (sizeNow == temp.length) {
                        resize(sizeNow * 2);
                    }
                    temp[sizeNow] = new LineSegment(p, other[index]);
                    sizeNow++;
                    k = index;
                }
            }
        }
    }

    private void resize(int length) {
        LineSegment[] newLS = new LineSegment[length];
        System.arraycopy(temp, 0, newLS, 0, temp.length);
        temp = newLS;
    }
    public int numberOfSegments() {
        return sizeNow;
    }

    public LineSegment[] segments() {
        LineSegment[] lines = new LineSegment[sizeNow];
        for (int i = 0; i < sizeNow; i++) {
            lines[i] = temp[i];
        }
        return lines;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
