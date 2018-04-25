package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 *
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * <p>
 * Find the sum of all the primes below two million.
 */
public class Euler010 {

	public static void main(String[] args) {

		final int max = 2_000_000;

		long res;
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Brute force", res, delta);
	}

	private static long calc1(int max) {

		long sum = 2;
		for (int i = 3; i < max; i = i + 2) {
			if (Utils.isPrime(i)) {
				sum += i;
			}
		}
		return sum;
	}
}
