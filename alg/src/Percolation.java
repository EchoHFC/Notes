import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF percolation;
    private WeightedQuickUnionUF full;
    private int n;
    private boolean[][] ids;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        percolation = new WeightedQuickUnionUF(n*n + 2);
        full = new WeightedQuickUnionUF(n*n + 2);

        ids = new boolean[n][n];
        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < ids[i].length; j++) {
                ids[i][j] = false;
            }
        }
    }

    public void open(int row, int col) {
        if ((row < 1) || (row > n)) {
            throw new IllegalArgumentException("row must be in [1,n]");
        }
        if ((col < 1) || (col > n)) {
            throw new IllegalArgumentException("col must be in [1,n]");
        }
        ids[row-1][col-1] = true;

        if (row == 1) {
            percolation.union(col - 1, n * n);
            full.union(col - 1, n * n);
        }

        if (row == n) {
            percolation.union(n * (n - 1) + col - 1, n * n + 1);
        }

        int index = (row - 1) * n + col - 1;

        if (row > 1) {
            if (isOpen(row - 1, col)) {
                percolation.union((row - 2) * n + col - 1, index);
                full.union((row - 2) * n + col - 1, index);
            }
        }

        if (row < n) {
            if (isOpen(row + 1, col)) {
                percolation.union(row * n + col - 1, index);
                full.union(row * n + col - 1, index);
            }
        }

        if (col > 1) {
            if (isOpen(row, col - 1)) {
                percolation.union((row - 1) * n + col - 2, index);
                full.union((row - 1) * n + col - 2, index);
            }
        }

        if (col < n) {
            if (isOpen(row, col + 1)) {
                percolation.union((row - 1) * n + col, index);
                full.union((row - 1) * n + col, index);
            }
        }

    }

    public boolean isOpen(int row, int col) {
        if ((row < 1) || (row > n)) {
            throw new IllegalArgumentException("row must be in [1,n]");
        }
        if ((col < 1) || (col > n)) {
            throw new IllegalArgumentException("col must be in [1,n]");
        }
        return ids[row-1][col-1];
    }

    public boolean isFull(int row, int col) {
        if ((row < 1) || (row > n)) {
            throw new IllegalArgumentException("row must be in [1,n]");
        }
        if ((col < 1) || (col > n)) {
            throw new IllegalArgumentException("col must be in [1,n]");
        }
        if (!isOpen(row, col)) {
            return false;
        }
        int index = full.find((row - 1) * n + col - 1);
        return index == full.find(n * n);
    }

    public int numberOfOpenSites() {
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ids[i][j]) {
                    num += 1;
                }
            }
        }
        return num;
    }

    public boolean percolates() {
        return percolation.find(n * n) == percolation.find(n * n + 1);
    }

    public static void main(String[] args) {

    }
}
