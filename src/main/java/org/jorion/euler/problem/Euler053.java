package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * There are exactly ten ways of selecting three from five, 12345:
 * <pre>
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 * </pre>
 * In combinatorics, we use the notation, C<sup>3</sup><sub>5</sub> = 10.
 * <p>
 * It is not until n = 23, that a value exceeds one-million: C<sup>10</sup><sub>23</sub> = 1144066.
 * <p>
 * How many, not necessarily distinct, values of C<sup>r</sup><sub>n</sub>, for 1 ≤ n ≤ 100, are greater than one-million?
 */
public class Euler053 {

	//--- Methods ---
	public static void main(String[] args) {

		final int max = 100;
		final int lim = 1_000_000;
		long res; // 4075
		long delta;

		Utils.start();
		res = calc1(max, lim);
		delta = Utils.stop();
		Utils.print("Simple   ", res, delta);
	}

	/**
	 * Compute directly Cpn.
	 */
	private static long calc1(int max, int lim) {

		final int min = 2;
		int count = 0;
		// int iter = 0;
		int start = 2;

		for (int n = min; n <= max; n++) {
			int middle = n / 2;
			for (int p = start; p <= middle; p++) {
				long cpn = Utils.combinationCPN(p, n);
				// iter++;
				if (cpn > lim) {
					int val = n - 2 * p + 1;
					count += val;
					start = p - 2;
					break;
				}
			}
		}
		// System.out.println("#calls of CPN: " + iter);
		return count;
	}

}
