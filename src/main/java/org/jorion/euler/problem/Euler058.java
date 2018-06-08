package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.
 * <pre>
 * 37 36 35 34 33 32 31
 * 38 17 16 15 14 13 30
 * 39 18  5  4  3 12 29
 * 40 19  6  1  2 11 28
 * 41 20  7  8  9 10 27
 * 42 21 22 23 24 25 26
 * 43 44 45 46 47 48 49
 * </pre>
 * It is interesting to note that the odd squares lie along the bottom right diagonal,
 * but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.
 * <p>
 * If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed.
 * If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?
 */
public class Euler058 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 10;
		long res; // 26241
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	/**
	 * Let be the center (1) coordinates(0, 0)
	 * <ul>
	 * <li>formula for bottom/right: 4n² + 4n + 1 = (2n + 1)² (never prime)
	 * <li>formula for top/right: 4n² - 2n + 1
	 * <li>formula for top/left: 4n² + 1
	 * <li>formula for bottom/left: 4n² + 2n + 1
	 * <ul>
	 * @see {@link Euler028}
	 */
	private static long calc1(int max) {

		int n = 0;
		int countPrime = 0;
		float percentage = (float) max / 100;

		while (true) {
			n++;
			if (PrimeUtils.isPrime6(ftr(n))) {
				countPrime++;
			}
			if (PrimeUtils.isPrime6(ftl(n))) {
				countPrime++;
			}
			if (PrimeUtils.isPrime6(fbl(n))) {
				countPrime++;
			}
			int countNumber = 4 * n + 1;
			float val = (float) countPrime / countNumber;
			if (val < percentage) {
				break;
			}
		}
		return 2 * n + 1;
	}

	@SuppressWarnings("unused")
	private static int fbr(int n) {

		return 4 * n * n + 4 * n + 1;
	}

	private static int ftr(int n) {

		return 4 * n * n - 2 * n + 1;
	}

	private static int ftl(int n) {

		return 4 * n * n + 1;
	}

	private static int fbl(int n) {

		return 4 * n * n + 2 * n + 1;
	}

}