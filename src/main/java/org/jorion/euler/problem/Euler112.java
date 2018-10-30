package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Working from left-to-right if no digit is exceeded by the digit to its left it is called an increasing number; for example, 134468.
 * <p>
 * Similarly if no digit is exceeded by the digit to its right it is called a decreasing number; for example, 66420.
 * <p>
 * We shall call a positive integer that is neither increasing nor decreasing a <b>bouncy</b> number; for example, 155349.
 * <p>
 * Clearly there cannot be any bouncy numbers below one-hundred, but just over half of the numbers below one-thousand (525) are bouncy. In fact, the
 * least number for which the proportion of bouncy numbers first reaches 50% is 538.
 * <p>
 * Surprisingly, bouncy numbers become more and more common and by the time we reach 21780 the proportion of bouncy numbers is equal to 90%.
 * <p>
 * Find the least number for which the proportion of bouncy numbers is exactly 99%.
 */
public class Euler112 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 99;
		long res; // 1587000
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	private static long calc1(int max) {

		int bouncy = 0;
		int num = 100;
		while (true) {
			if (!isIncreasingOrDecreasing(num)) {
				bouncy++;
			}
			if (max * num <= bouncy * 100) {
				break;
			}
			num++;
		}
		return num;
	}

	private static boolean isIncreasingOrDecreasing(int num) {

		if (num < 100) {
			return true;
		}

		boolean ok = true;
		boolean inc = false;
		boolean dec = false;
		int current = -1; // unitialized

		while (num > 0) {
			int next = num % 10;
			// first digit
			if (current != -1) {
				// not sure yet whether it's increasing or decreasing
				if (!inc && !dec) {
					inc = next > current;
					dec = next < current;
				}
				// increasing
				else if (inc && next < current) {
					ok = false;
					break;
				}
				// decreasing
				else if (dec && next > current) {
					ok = false;
					break;
				}
			}
			current = next;
			num /= 10;
		}

		return ok;
	}

}