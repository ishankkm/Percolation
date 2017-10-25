import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final int trials;
	private double[] trialResults;

	public PercolationStats(int n, int trials) {
		if (n < 1 || trials < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}

		this.trials = trials;
		trialResults = new double[trials];
		runTrials(n);
	}

	private void runTrials(int nGrid) {

		for (int i = 0; i < trials; i++) {

			Percolation perc = new Percolation(nGrid);
			while (!perc.percolates()) {
				int random = StdRandom.uniform(nGrid * nGrid);
				if (perc.isOpen(random / nGrid + 1, random % nGrid + 1)) {
					perc.open(random / nGrid + 1, random % nGrid + 1);
				}
			}

			trialResults[i] = (double) perc.numberOfOpenSites()
					/ (nGrid * nGrid);
		}
	}

	public double mean() {
		return StdStats.mean(trialResults);
	}

	public double stddev() {
		return StdStats.stddev(trialResults);
	}

	public double confidenceLo() {

		return StdStats.mean(trialResults)
				- (1.96 * StdStats.stddev(trialResults)) / Math.sqrt(trials);

	}

	public double confidenceHi() {

		return StdStats.mean(trialResults)
				+ (1.96 * StdStats.stddev(trialResults)) / Math.sqrt(trials);

	}

	public static void main(String[] args) {

		/*
		 * PercolationStats ps = new PercolationStats(200, 100);
		 * System.out.println("Mean\t= " + ps.mean());
		 * System.out.println("SD\t= " + ps.stddev());
		 * System.out.println("95% confidence interval\t= [" + ps.confidenceLo()
		 * + ", " + ps.confidenceHi() + "]");
		 */
	}

}
