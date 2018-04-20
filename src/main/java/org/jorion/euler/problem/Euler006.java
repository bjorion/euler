package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * The sum of the squares of the first ten natural numbers is,^
 * <pre>
 * 1^2 + 2^2 + ... + 10^2 = 385
 * </pre>
 * The square of the sum of the first ten natural numbers is,
 * <pre>
 * (1 + 2 + ... + 10)^2 = 55^2 = 3025
 * </pre>
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
 *
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class Euler006 {

	public static void main(String[] args) {

		final int max = 1000;

		long res; // 100 => 25.164.150
		long start, delta;

		start = System.nanoTime();
		res = calc1(max);
		delta = System.nanoTime() - start;
		Utils.print("Simple O(n)     ", res, delta);

		start = System.nanoTime();
		res = calc2(max);
		delta = System.nanoTime() - start;
		Utils.print("Less Simple O(n)", res, delta);

		start = System.nanoTime();
		res = calc3(max);
		delta = System.nanoTime() - start;
		Utils.print("Best O(1)       ", res, delta);

	}

	/**
	 * Brute force to compute sum of square.
	 */
	static long calc1(final int n) {

		long sumOfSquare = 0;
		for (int i = 1; i <= n; i++) {
			sumOfSquare += i * i;
		}
		long sum = (n + 1) * n / 2;
		long squareOfSum = sum * sum;
		return squareOfSum - sumOfSquare;
	}

	/**
	 * Smartest way to compute sum of square.
	 */
	static long calc2(final int n) {

		long sumOfSquare = 1;
		long val = 1;
		for (int i = 2; i <= n; i++) {
			val += (2 * i - 1);
			sumOfSquare += val;
		}
		long sum = (n + 1) * n / 2;
		long squareOfSum = sum * sum;
		return squareOfSum - sumOfSquare;
	}

	/**
	 * Using formula: sumOfSquare = f(n) = (2n + 1)(n + 1)n/6
	 */
	static long calc3(final int n) {

		long sumOfSquare = (2 * n + 1) * (n + 1) * n / 6;
		long sum = (n + 1) * n / 2;
		long squareOfSum = sum * sum;
		return squareOfSum - sumOfSquare;
	}
}
