package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * <p>
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class Euler004 {

	/**
	 * The product of two 3-digit numbers is a 6-digit numbers (at the most): abc * def = ghijkl.
	 * We are looking for a palindromic number like abccba (ex: 987789).
	 */
	public static void main(String[] args) {

		int[] res;
		long start, delta;

		start = System.nanoTime();
		res = calc1(999, 101);
		delta = System.nanoTime() - start;
		Utils.print("Brute force        " + res[1] + "*" + res[2], res[0], delta);

		start = System.nanoTime();
		res = calc2(999, 101);
		delta = System.nanoTime() - start;
		Utils.print("Factor 11          " + res[1] + "*" + res[2], res[0], delta);

		start = System.nanoTime();
		res = calc3(999, 101);
		delta = System.nanoTime() - start;
		Utils.print("Factor 11 improved " + res[1] + "*" + res[2], res[0], delta);
	}

	/**
	 * Brute force. O(2).
	 */
	static int[] calc1(final int max, final int min) {

		int res = 0, v1 = 0, v2 = 0;
		for (int i = max; i >= min; i--) {
			for (int j = max; j >= min; j--) {
				int c = i * j;
				if (Utils.isPalindromic(c) && c > res) {
					v1 = i;
					v2 = j;
					res = c;
					// break;
				}
			}
		}
		return new int[] { res, v1, v2 };
	}

	/**
	 * Use the fact that one of the two factor must be a multiple of 11.
	 */
	static int[] calc2(final int max, final int min) {

		int res = 0, v1 = 0, v2 = 0;
		for (int i = max; i >= min; i--) {
			for (int j = 990; j >= 110; j = j - 11) {
				int c = i * j;
				if (Utils.isPalindromic(c) && c > res) {
					v1 = i;
					v2 = j;
					res = c;
				}
			}
		}
		return new int[] { res, v1, v2 };
	}

	/**
	 * As calc2, but with j <= i
	 */
	static int[] calc3(final int max, final int min) {

		int res = 0, v1 = 0, v2 = 0;
		for (int i = max; i >= min; i--) {
			for (int j = (i / 11) * 11; j >= min; j = j - 11) {
				int c = i * j;
				if (Utils.isPalindromic(c) && c > res) {
					v1 = i;
					v2 = j;
					res = c;
					// break;
				}
			}
		}
		return new int[] { res, v1, v2 };
	}

}
