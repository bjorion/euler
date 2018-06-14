package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 *
 * If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
 * <p>
 * Not all numbers produce palindromes so quickly. For example,
 * <pre>
 * 349 + 943 = 1292,
 * 1292 + 2921 = 4213
 * 4213 + 3124 = 7337
 * </pre>
 * That is, 349 took three iterations to arrive at a palindrome.
 * <p>
 * Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome.
 * A number that never forms a palindrome through the reverse and add process is called a Lychrel number.
 * Due to the theoretical nature of these numbers, we shall assume that a number is Lychrel until proven otherwise.
 * In addition you are given that for every number below ten-thousand,
 * it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing power that exists,
 * has managed so far to map it to a palindrome.
 * <p>
 * In fact, 10677 is the first number to be shown to require over fifty iterations before producing a palindrome:
 * 4_668_731_596_684_224_866_951_378_664 (53 iterations, 28-digits).
 * <p>
 * Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.
 * <p>
 * How many Lychrel numbers are there below ten-thousand?
 */
public class Euler055 {

	// --- Methods ---
	public static void main(String[] args) {

		// Long.MAX_VALUE = 9_223_372_036_854_775_807 (9 milliars de milliards, 19 digits)
		final int max = 10_000;
		long res;
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	private static long calc1(int limit) {

		int max = 50;
		int count = 0;
		for (int i = 1; i <= limit; i++) {
			boolean lychrel = true;
			BigInteger val = BigInteger.valueOf(i);
			for (int j = 0; j < max; j++) {
				String rev = WordUtils.reverse(val.toString());
				val = val.add(new BigInteger(rev));
				if (WordUtils.isPalindromic(val.toString())) {
					lychrel = false;
					break;
				}
			}
			if (lychrel) {
				// System.out.println("lynchrel: " + i);
				count++;
			}
		}
		return count;
	}

}