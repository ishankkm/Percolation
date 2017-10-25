/* import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner; */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private final WeightedQuickUnionUF uf;
	private final WeightedQuickUnionUF ufFull;
	private final int nGrid;
	private boolean[][] grid;

	public Percolation(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		uf = new WeightedQuickUnionUF(n * n + 2);
		ufFull = new WeightedQuickUnionUF(n * n + 1);
		grid = new boolean[n][n];
		nGrid = n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = false;
			}
		}
	}

	private void validateIndices(int row, int col) {

		if (row < 1 || row > nGrid || col < 1 || col > nGrid) {
			throw new IllegalArgumentException("Index Out Of Bounds");
		}
	}

	public void open(int row, int col) {

		validateIndices(row, col);

		if (isOpen(row, col))
			return;

		row--;
		col--;

		if (nGrid == 1) {
			uf.union(row * nGrid + col, nGrid * nGrid);
			uf.union(row * nGrid + col, nGrid * nGrid + 1);

			ufFull.union(row * nGrid + col, nGrid * nGrid);
		} else if (row == 0) {
			uf.union(row * nGrid + col, nGrid * nGrid);
			ufFull.union(row * nGrid + col, nGrid * nGrid);
		} else if (row == nGrid - 1) {
			uf.union(row * nGrid + col, nGrid * nGrid + 1);
		}

		if (col > 0) {
			if (grid[row][col - 1]
					&& !ufFull.connected(row * nGrid + col, row * nGrid + col
							- 1)) {
				uf.union(row * nGrid + col, row * nGrid + col - 1);
				ufFull.union(row * nGrid + col, row * nGrid + col - 1);
			}
		}
		if (col < nGrid - 1) {
			if (grid[row][col + 1]
					&& !ufFull.connected(row * nGrid + col, row * nGrid + col
							+ 1)) {
				uf.union(row * nGrid + col, row * nGrid + col + 1);
				ufFull.union(row * nGrid + col, row * nGrid + col + 1);
			}
		}
		if (row > 0) {
			if (grid[row - 1][col]
					&& !ufFull.connected(row * nGrid + col, (row - 1) * nGrid
							+ col)) {
				uf.union(row * nGrid + col, (row - 1) * nGrid + col);
				ufFull.union(row * nGrid + col, (row - 1) * nGrid + col);
			}
		}
		if (row < nGrid - 1) {
			if (grid[row + 1][col]
					&& !ufFull.connected(row * nGrid + col, (row + 1) * nGrid
							+ col)) {
				uf.union(row * nGrid + col, (row + 1) * nGrid + col);
				ufFull.union(row * nGrid + col, (row + 1) * nGrid + col);
			}
		}
		grid[row][col] = true;
	}

	public boolean isOpen(int row, int col) {

		validateIndices(row, col);

		row--;
		col--;
		if (grid[row][col])
			return true;
		
			return false;
	}

	public boolean isFull(int row, int col) {

		validateIndices(row, col);
		row--;
		col--;

		if (ufFull.connected(row * nGrid + col, nGrid * nGrid))
			return true;
		return false;

	}

	public int numberOfOpenSites() {

		int count = 0;

		for (int i = 0; i < nGrid; i++) {
			for (int j = 0; j < nGrid; j++) {
				if (grid[i][j])
					count++;
			}
		}
		return count;
	}

	public boolean percolates() {
		return uf.connected(nGrid * nGrid, nGrid * nGrid + 1);
	}

	public static void main(String[] args) {

		/* File f = new File("src/percolation/input1.txt");
		try {
			Scanner s = new Scanner(new FileReader(f));
			int n = s.nextInt();
			Percolation perc = new Percolation(n);

			while (s.hasNext()) {
				int p = s.nextInt();
				int q = s.nextInt();
				if (perc.isOpen(p, q))
					continue;
				perc.open(p, q);
				System.out.println(p + " " + q + ": " + perc.isFull(p, q));

				// System.out.println(p + " " + q +": "+ perc.isOpen(p, q));
			}
			s.close();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (perc.isFull(i + 1, j + 1))
						System.out.print("1 ");
					else
						System.out.print("0 ");
				}
				System.out.println();
			}
//			System.out.println(perc.isFull(4, 4));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} */
	}
}
