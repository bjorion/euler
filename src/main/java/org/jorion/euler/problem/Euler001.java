package org.jorion.euler.problem;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * <p>
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.</p>
 */
public class Euler001 {

	public static void main(String[] args) {

		final int max = 1_000;
		final int v1 = 3;
		final int v2 = 5;

		int sum;
		long start, delta;

		start = System.nanoTime();
		sum = calc1(max, v1, v2);
		delta = System.nanoTime() - start;
		print("Method 1 - best:O(1)      ", sum, delta);

		start = System.nanoTime();
		sum = calc2(max, v1, v2);
		delta = System.nanoTime() - start;
		print("Method 2 - classic:O(n)   ", sum, delta);

		start = System.nanoTime();
		sum = calc3(max, v1, v2);
		delta = System.nanoTime() - start;
		print("Method 3 - smart:O(n)     ", sum, delta);

		start = System.nanoTime();
		sum = calc4(max, v1, v2);
		delta = System.nanoTime() - start;
		print("Method 4 - stream:O(n)    ", sum, delta);

		start = System.nanoTime();
		sum = calc5(max, v1, v2);
		delta = System.nanoTime() - start;
		print("Method 5 - parallel:O(n)  ", sum, delta);
	}

	/**
	 * Best solution. O(1).
	 */
	public static int calc1(int max, int v1, int v2) {

		max--;
		int s1 = v1 * sum(max / v1);
		int s2 = v2 * sum(max / v2);
		int v12 = v1 * v2;
		int s12 = v12 * sum(max / v12);
		int sum = s1 + s2 - s12;
		return sum;
	}

	/**
	 * Brute force. O(n).
	 */
	public static int calc2(int max, int v1, int v2) {

		int sum = 0;
		for (int i = 1; i < max; i++) {
			if (i % v1 == 0 || i % v2 == 0) {
				sum += i;
			}
		}
		return sum;
	}

	/**
	 * Brute force where we avoid a division. O(n).
	 */
	public static int calc3(int max, int v1, int v2) {

		int sum = 0;
		int s1 = v1, s2 = v2;
		for (int i = 1; i < max; i++) {
			if (i == s1 || i == s2) {
				sum += i;
				if (i == s1) {
					s1 += v1;
				}
				if (i == s2) {
					s2 += v2;
				}
			}
		}
		return sum;
	}

	/**
	 * Brute force using Java 8 stream. O(n).
	 */
	public static int calc4(int max, int v1, int v2) {

		OptionalInt sum = IntStream.range(1, max).filter(x -> {
			return x % v1 == 0 || x % v2 == 0;
		}).reduce(Integer::sum);

		return sum.orElse(0);
	}

	/**
	 * Brute force using Java 8 parallel stream. O(n).
	 */
	public static int calc5(int max, int v1, int v2) {

		OptionalInt sum = IntStream.range(1, max).parallel().filter(x -> {
			return x % v1 == 0 || x % v2 == 0;
		}).reduce(Integer::sum);

		return sum.orElse(0);
	}

	/**
	 * @return the sum from 1 to n
	 */
	private static int sum(int n) {

		return (n + 1) * n / 2;
	}

	private static void print(String msg, int sum, long delta) {

		String time = String.format("%6d µs", delta / 1000);
		System.out.println(msg + " - Sum: " + sum + " - time: " + time);
	}
}
