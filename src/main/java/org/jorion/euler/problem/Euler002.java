package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
 * <pre>
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144...
 * </pre>
 * By considering the terms in the Fibonacci sequence whose values do not exceed four millions, find the sum of the even-valued terms.
 */
public class Euler002 {

	public static void main(String[] args) {

		final long max = 4 * 1_000_000;

		long sum = 0; // 4.613.732
		long start, delta;

		start = System.nanoTime();
		sum = calc1(max);
		delta = System.nanoTime() - start;
		Utils.print("Simple solution (good)  ", sum, delta);

		start = System.nanoTime();
		sum = calc2(max);
		delta = System.nanoTime() - start;
		Utils.print("Simple solution wo test ", sum, delta);

		start = System.nanoTime();
		sum = calc3(max);
		delta = System.nanoTime() - start;
		Utils.print("Recursive solution (bad)", sum, delta);

	}

	/**
	 * Simple solution.
	 */
	static long calc1(long max) {

		int a = 1, b = 1;
		long sum = 0;

		while (true) {
			// if b is even
			if ((b & 0b1) == 0) {
				sum += b;
			}
			int c = a + b;
			if (c > max) {
				break;
			}
			a = b;
			b = c;
		}

		return sum;
	}

	/**
	 * Improve simple solution (without test).
	 */
	static long calc2(long max) {

		int a = 1, b = 1;
		long sum = 0;

		while (true) {
			int c = a + b; // c is even
			if (c > max) {
				break;
			}
			sum += c;
			a = b + c;
			b = c + a;
		}

		return sum;
	}

	/**
	 * Recursive solution (bad: risk of stack overflow).
	 */
	static long calc3(long max) {

		long acc = 0;
		return fb(1, 1, max, acc);
	}

	private static long fb(int a, int b, long max, long acc) {

		// if b is even
		if ((b & 0b1) == 0) {
			acc += b;
		}
		int c = a + b;
		if (c > max) {
			return acc;
		}
		return fb(b, c, max, acc);
	}

}
