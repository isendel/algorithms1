import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * User: viktorp
 * Date: 9/19/15
 * Time: 9:00 PM
 */
public class PercolationStats {

    private final int N;
    private final int T;
    private final double[] a;

    /**
     * perform T independent experiments on an N-by-N grid
     *
     * @param N size of grid
     * @param T number of experiments
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.T = T;

        a = new double[T];
        for (int t = 0; t < T; t++) {
            Percolation p = new Percolation(N);
            int openCount = 0;
            while (!p.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!p.isOpen(i, j)) {
                    p.open(i, j);
                    openCount++;
                }
            }
            a[t] = openCount / (double) (N * N);
        }
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(a);
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(a);
    }

    /**
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        return mean - (1.96 * stddev) / Math.sqrt(T);
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {

        double mean = mean();
        double stddev = stddev();
        return mean + (1.96 * stddev) / Math.sqrt(T);

    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        StdOut.printf("mean %10.3f\n", percolationStats.mean());
        StdOut.printf("stddev %10.3f\n", percolationStats.stddev());
        StdOut.printf("95 confidence interval " + percolationStats.confidenceLo() +", "  + percolationStats.confidenceHi());
    }
}
