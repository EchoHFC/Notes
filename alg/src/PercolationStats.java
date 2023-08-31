import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation per;
    private double[] p;
    private int trails;

    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0");
        }

        p = new double[trials];
        this.trails = trials;

        // 进行trials次实验
        for (int i = 0; i < trials; i++) {
            per = new Percolation(n);
            while (!per.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                per.open(row, col);
            }
            p[i] = (double) per.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(p);
    }

    public double stddev() {
        return StdStats.stddev(p);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trails);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trails);
    }

    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);
        PercolationStats perStat = new PercolationStats(n, trials);
        double mean = perStat.mean();
        double stddev = perStat.stddev();
        double lower = perStat.confidenceLo();
        double higher = perStat.confidenceHi();

        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = ["  + lower + ", " + higher + " ]");
    }
}