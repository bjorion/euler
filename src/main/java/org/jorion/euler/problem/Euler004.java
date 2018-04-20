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
		res = calc1(999);
		delta = System.nanoTime() - start;

		Utils.print("Brute force - O(n2) - " + res[1] + "*" + res[2], res[0], delta);
	}

	/**
	 * Brute force. O(2).
	 */
	static int[] calc1(final int max) {

		int res = 0, v1 = 0, v2 = 0;
		for (int i = max; i > 101; i--) {
			for (int j = max; j > 101; j--) {
				int c = i * j;
				if (isPalindromic(c) && c > res) {
					v1 = i;
					v2 = j;
					res = c;
				}
			}
		}

		return new int[] { res, v1, v2 };
	}

	private static boolean isPalindromic(int val) {

		boolean flag = true;
		String str = Integer.toString(val);
		int len = str.length();
		for (int i = 0; i < (len + 1) / 2; i++) {
			if (str.charAt(i) != str.charAt(len - i - 1)) {
				flag = false;
				break;
			}
		}

		return flag;
	}
}
