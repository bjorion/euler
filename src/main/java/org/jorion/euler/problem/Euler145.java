package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * Some positive integers n have the property that the sum [ n + reverse(n) ]
 * consists entirely of odd (decimal) digits. For instance, 36 + 63 = 99 and 409
 * + 904 = 1313. We will call such numbers reversible; so 36, 63, 409, and 904
 * are reversible. Leading zeroes are not allowed in either n or reverse(n).
 * <p>
 * There are 120 reversible numbers below one-thousand.
 * <p>
 * How many reversible numbers are there below one-billion (109)?
 */
public class Euler145 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 1_000_000_000;
		long res; // 608720
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
		
		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}
	
	/**
	 * Algorithm without using time-consuming methods like toString() or parse().
	 * <p>
	 * Time for 1_000_000_000: around 37 sec.
	 */
	private static long calc2(int max) {
	
		int res = 0;
		int count = 0;
		for (int i = 11; i < max; i++) {
			if (++count == 10) {
				count = 0;
				continue;
			}
			String str1 = Integer.toString(i);
			int len = str1.length();
			int report = 0;
			boolean ok = true;
			for (int k = 0; k < len; k++) {
				int ch0 = str1.charAt(k) - 48;
				int ch1 = str1.charAt(len - 1 - k) - 48;
				int sum = ch0 + ch1 + report;
				if (sum % 2 == 0) {
					ok = false;
					break;
				}
				report = (sum >= 10) ? 1 : 0;
			}
			if (ok) {
				res++;
				// System.out.println("i: " + i);
			}
		}
		return res;
	}
	

	/**
	 * Time for 1_000_000_000: around 70 sec (from 110 sec).
	 */
	private static long calc1(int max) {

		int res = 0;
		int count = 0;
		for (int i = 11; i < max; i++) {
			// we avoid numbers whose last digit = 0
			if (++count == 10) {
				count = 0;
				continue;
			}
			
			String str1 = Integer.toString(i);
			char ch0 = str1.charAt(0);
			char chN = str1.charAt(str1.length() - 1);
			if ((ch0 + chN) % 2 == 0) {
				continue;
			}
			String str2 = WordUtils.reverse(str1);
			int j = Integer.parseInt(str2);
			if (j <= i) {
				continue;
			}
			if (containsOnlyOddDigits(i + j)) {
				System.out.println("i: " + i + ", j: " + j + ", sum: " + (i+j));
				res++;
			}
		}

		return res * 2;
	}

	private static boolean containsOnlyOddDigits(int n) {

		boolean ok = true;
		while (ok && n > 0) {
			int d = n % 10;
			if (d % 2 == 0) {
				ok = false;
				break;
			}
			n /= 10;
		}
		return ok;
	}
}