package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * It is possible to write five as a sum in exactly six different ways:
 *
 * <pre>
 * 4 + 1
 * 3 + 2
 * 3 + 1 + 1
 * 2 + 2 + 1
 * 2 + 1 + 1 + 1
 * 1 + 1 + 1 + 1 + 1
 * </pre>
 *
 * How many different ways can one hundred be written as a sum of at least two positive integers?
 */
public class Euler076 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 20;
		long res;
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	private static long calc1(int max) {

		return 0;
	}

}