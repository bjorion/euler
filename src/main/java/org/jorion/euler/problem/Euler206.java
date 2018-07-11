package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
 * where each “_” is a single digit.
 * <p>
 * Since last digit = 0, last digit of square root must be 0, so last unknown digit must be 0 as well (since 10 * 10 = 100).
 * Square has form 1_2_3_4_5_6_7_8_900, so I think last two digits of square root must be "1 _ _ _ _ _ _ _ 3 0" or "1 _ _ _ _ _ _ _ 7 0"
 */
public class Euler206 {

	// --- Methods ---
	public static void main(String[] args) {

		final long min = 1010101030;
		final long max = 1389026570;
		long res; // 1389019170 => 1929374254627488900
		long delta;

		Utils.start();
		res = calc1(min, max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	private static long calc1(long min, long max) {

		long res = 0;
		boolean fourty = true;
		for (long val = min; val <= max;) {
			long square = val * val;
			if (isValid(square)) {
				res = val;
				// System.out.println("val: " + val + ", square: " + square);
				break;
			}
			val = val + ((fourty) ? 40 : 60);
			fourty = !fourty;
		}
		return res;
	}

	private static boolean isValid(long val) {

		boolean ok = true;
		// we suppose last digits are 900, so there is no need to check them
		val = val / 10000;
		long expected = 8;
		while (val > 0) {
			long mod = val % 10;
			if (mod != expected) {
				ok = false;
				break;
			}
			expected--;
			val /= 100;
		}
		return ok;
	}

}