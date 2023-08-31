import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] temp;
    private int sizeNow;
    public BruteCollinearPoints(Point[] points) {
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

        temp = new LineSegment[8];
        for (int i = 0; i < pointsCopy.length - 3; i++) {
            for (int j = i + 1; j < pointsCopy.length - 2; j++) {
                for (int k = j + 1; k < pointsCopy.length - 1; k++) {
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        if ((Double.compare(pointsCopy[i].slopeTo(pointsCopy[j]), pointsCopy[i].slopeTo(pointsCopy[k]))==0) &&(Double.compare(pointsCopy[i].slopeTo(pointsCopy[j]) ,pointsCopy[i].slopeTo(pointsCopy[l]))==0)) {
                            if (sizeNow == temp.length) {
                                resize(sizeNow * 2);
                            }
                            temp[sizeNow] = new LineSegment(pointsCopy[i], pointsCopy[l]);
                            sizeNow++;
                        }
                    }
                    
                }
                
            }
        }
    }

    public int numberOfSegments() {
        return sizeNow;
    }

    private void resize(int length) {
        LineSegment[] newLS = new LineSegment[length];
        System.arraycopy(temp, 0, newLS, 0, temp.length);
        temp = newLS;
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