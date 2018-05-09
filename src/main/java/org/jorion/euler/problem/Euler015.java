package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
 * <p>
 * How many such routes are there through a 20×20 grid?
 */
public class Euler015 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 20;
		long res; // 137846528820
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	/**
	 * Use formula C<sup>p</sup><sub>n</sub> where n = max * 2 and p = max
	 */
	private static long calc1(int max) {

		return Utils.combinationCPN(max, max * 2);
	}

}