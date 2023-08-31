import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
public class KdTree {

    private class Node {
        private Point2D p;
        private Node left;
        private Node right;
        private RectHV rect;
        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }
    private Node Root;
    private int size;
    private static final boolean EVEN = true;

    public KdTree() {
        Root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The argument should not be null");
        }
        Root = insert(Root, p, EVEN, new RectHV(0.0, 0.0, 1.0, 1.0));
        size++;
    }

    private Node insert(Node root, Point2D point, boolean isEven, RectHV rect) {
        if (root == null) {
            return new Node(point, rect);
        }
        if (root.p.equals(point)) {
            size--;
            return root;
        }
        if (isEven) {
            if (root.p.x() > point.x()) {
                RectHV rect1 = new RectHV(root.rect.xmin(), root.rect.ymin(), root.p.x(), root.rect.ymax());
                root.left = insert(root.left, point, !isEven, rect1);
            }
            else {
                RectHV rect1 = new RectHV(root.p.x(), root.rect.ymin(), root.rect.xmax(), root.rect.ymax());
                root.right = insert(root.right, point, !isEven, rect1);
            }
        }
        else {
            if (root.p.y() > point.y()) {
                RectHV rect1 = new RectHV(root.rect.xmin(), root.rect.ymin(), root.rect.xmax(), root.p.y());
                root.left = insert(root.left, point, !isEven, rect1);
            }
            else {
                RectHV rect1 = new RectHV(root.rect.xmin(), root.p.y(), root.rect.xmax(), root.rect.ymax());
                root.right = insert(root.right, point, !isEven, rect1);
            }
        }
        return root;
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The argument should not be null");
        }
        Node x = Root;
        boolean isEven = EVEN;
        while (x != null) {
            if (x.p.equals(p)) {
                return true;
            }
            if (isEven) {
                if (x.p.x() > p.x()) {
                    x = x.left;
                }
                else {
                    x = x.right;
                }
            }
            else {
                if (x.p.y() > p.y()) {
                    x = x.left;
                }
                else {
                    x = x.right;
                }
            }
            isEven = !isEven;
        }
        return false;
    }

    public void draw() {
        draw(Root);
    }

    private void draw(Node x) {
        if (x == null) {
            return;
        }
        x.p.draw();
        draw(x.right);
        draw(x.left);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("The argument should not be null");
        }
        Stack<Point2D> stack = new Stack<Point2D>();
        range(rect, Root, stack);
        return stack;
    }

    private void range(RectHV rect, Node x, Stack<Point2D> stack) {
        if (x == null) {
            return;
        }
        if (rect.contains(x.p)) {
            stack.push(x.p);
        }
        if ((x.left != null) && (x.left.rect.intersects(rect))) {
            range(rect, x.left, stack);
        }
        if ((x.right != null) && (x.right.rect.intersects(rect))) {
            range(rect, x.right, stack);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("The argument should not be null");
        }
        return nearest(p, Root, EVEN);
    }

    private Point2D nearest(Point2D p, Node x, boolean isEven) {
        Point2D qLeft = null;
        Point2D qRight = null;
        double disLeft = Double.POSITIVE_INFINITY;
        double disRight = Double.POSITIVE_INFINITY;
        if (x == null) {
            return null;
        }
        double dis = p.distanceSquaredTo(x.p);
        if (isEven) {
            if (p.x() < x.p.x()) {
                qLeft = nearest(p, x.left, !isEven);

                if (x.right != null && (qLeft == null || qLeft.distanceSquaredTo(p) > x.right.rect.distanceSquaredTo(p))) {
                    qRight = nearest(p, x.right, !isEven);
                }
            } else if (p.x() > x.p.x()) {
                qRight = nearest(p, x.right, !isEven);
                if (x.left != null && (qRight == null || qRight.distanceSquaredTo(p) > x.left.rect.distanceSquaredTo(p))) {
                    qLeft = nearest(p, x.left, !isEven);
                }
            }
            else {
                qLeft = nearest(p, x.left, !isEven);
                qRight = nearest(p, x.right, !isEven);
            }
        }
        else {
            if (p.y() < x.p.y()) {
                qLeft = nearest(p, x.left, !isEven);
                if (x.right != null && (qLeft == null || qLeft.distanceSquaredTo(p) > x.right.rect.distanceSquaredTo(p))) {
                    qRight = nearest(p, x.right, !isEven);
                }
            } else if (p.y() > x.p.y()) {
                qRight = nearest(p, x.right, !isEven);
                if (x.left != null && (qRight == null || qRight.distanceSquaredTo(p) > x.left.rect.distanceSquaredTo(p))) {
                    qLeft = nearest(p, x.left, !isEven);
                }
            }
            else {
                qLeft = nearest(p, x.left, !isEven);
                qRight = nearest(p, x.right, !isEven);
            }
        }

        if (qLeft != null) {
            disLeft = p.distanceSquaredTo(qLeft);
        }


        if (qRight != null) {
            disRight = p.distanceSquaredTo(qRight);
        }

        Point2D q = disRight > disLeft ? qLeft : qRight;
        if (q == null) {
            return x.p;
        }
        q = dis > p.distanceSquaredTo(q) ? q : x.p;
        return q;
    }


    public static void main(String[] args) {

    }
}
