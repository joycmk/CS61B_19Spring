package hw2;

import edu.princeton.cs.introcs.*;


public class PercolationStats {
    private Percolation example;
    private int times;

    private double[] data;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N<=0 || T <=0) {
            throw new java.lang.IllegalArgumentException();
        }
        times = T;
        data = new double[times];
        for (int i=0; i<times ; i++) {
            example = pf.make(N);

            while (!example.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                example.open(row,col);
            }
            data[i] = example.numberOfOpenSites()/N/N;
        }
    }   // perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(data);
    }                                           // sample mean of percolation threshold
    public double stddev(){
        return StdStats.stddev(data);
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - 1.96*stddev()/Math.sqrt(times);
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96*stddev()/Math.sqrt(times);
    }                                // high endpoint of 95% confidence interval

}
