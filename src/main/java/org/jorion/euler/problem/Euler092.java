package org.jorion.euler.problem;

import java.util.HashSet;
import java.util.Set;

import org.jorion.euler.util.Utils;

/**
 * A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before.
 * <p>
 * For example,
 * <pre>
 * 44 → 32 → 13 → 10 → 1 → 1
 * 85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89
 * </pre>
 * Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop.
 * What is most amazing is that EVERY starting number will eventually arrive at 1 or 89.
 * <p>
 * How many starting numbers below ten million will arrive at 89?
 */
public class Euler092 {

	static int count = 0;

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 10_000_000;
		long res; // 8_581_146
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple   ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Advanced ", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Best     ", res, delta);
	}

	/**
	 * Around 8.5s
	 */
	private static long calc1(int max) {

		Set<Integer> l01 = new HashSet<>();
		l01.add(1);
		Set<Integer> l89 = new HashSet<>();
		l89.add(89);

		for (int i = 1; i < max; i++) {
			int n = i;
			while (!l89.contains(n) && !l01.contains(n)) {
				n = countSumSquareDigit(n);
			}
			if (l89.contains(n)) {
				l89.add(i);
			} else {
				l01.add(i);
			}
		}
		return l89.size();
	}

	/**
	 * Make the test only once for each number.
	 */
	private static long calc2(int max) {

		Set<Integer> l01 = new HashSet<>();
		l01.add(1);
		Set<Integer> l89 = new HashSet<>();
		l89.add(89);

		Set<Integer> tmp = new HashSet<>();
		for (int i = 1; i < max; i++) {
			int n = i;
			if (l89.contains(n) || l01.contains(n)) {
				continue;
			}
			tmp.clear();
			while (!l89.contains(n) && !l01.contains(n)) {
				tmp.add(n);
				n = countSumSquareDigit(n);
			}
			if (l89.contains(n)) {
				l89.addAll(tmp);
			} else {
				l01.addAll(tmp);
			}
		}
		return l89.size();
	}

	/**
	 * Without a Set, but with two boolean arrays.
	 */
	private static long calc3(int max) {

		final int size = (int) Math.log10(max) * 100;

		boolean[] b01 = new boolean[size];
		boolean[] b89 = new boolean[size];
		b01[1] = true;
		b89[89] = true;

		int count = 0;
		for (int i = 1; i < max; i++) {
			int n = i;
			if (n >= size) {
				n = countSumSquareDigit(n);
			}
			while (!b01[n] && !b89[n]) {
				n = countSumSquareDigit(n);
			}
			// b89[n] = true
			if (b89[n]) {
				if (i < size) {
					b89[i] = true;
				}
				count++;
			}
			// b01[n] = true
			else {
				if (i < size) {
					b01[i] = true;
				}
			}
		}
		return count;
	}

	private static int countSumSquareDigit(int n) {

		int sum = 0;
		while (n > 0) {
			int r = n % 10;
			sum += r * r;
			n /= 10;
		}
		return sum;
	}

}
