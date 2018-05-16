package org.jorion.euler.problem;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * The first two consecutive numbers to have two distinct prime factors are:
 * <pre>
 * 14 = 2 × 7
 * 15 = 3 × 5
 * </pre>
 * The first three consecutive numbers to have three distinct prime factors are:
 * <pre>
 * 644 = 2^2 ×  7 × 23
 * 645 = 3   ×  5 × 43
 * 646 = 2   × 17 × 19
 * </pre>
 * Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?
 */
public class Euler047 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 4;

		int res; // 134043
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple", res, delta);
	}

	private static int calc1(int max) {

		int n = 10;
		int res = 0;

		boolean found = false;
		int count = 0;
		while (!found) {
			int size = MathUtils.findPrimeFactors(n).size();
			n++;
			if (size < max) {
				count = 0;
				res = n;
				continue;
			}
			count++;
			found = (count >= max);
		}

		return res;
	}

}
