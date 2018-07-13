package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * A googol (10100) is a massive number: one followed by one-hundred zeros; 100100 is almost unimaginably large: one followed by two-hundred zeros.
 * Despite their size, the sum of the digits in each number is only 1.
 * <p>
 * Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?
 */
public class Euler056 {

	// --- Methods ---
	public static void main(String[] args) {

		final int min = 90;
		final int max = 100;
		long res; // 972 (15ms)
		long delta;

		Utils.start();
		res = calc1(min, max);
		delta = Utils.stop();
		Utils.print("Brute force ", res, delta);
	}

	private static long calc1(int min, int max) {

		int res = 0;
		for (int i = min; i < max; i++) {
			if (i % 10 == 0) {
				continue;
			}
			for (int j = min; j < max; j++) {
				BigInteger bi = new BigInteger(Integer.toString(i));
				BigInteger val = bi.pow(j);
				int count = WordUtils.countDigits(val.toString());
				if (count > res) {
					res = count;
				}
			}
		}
		return res;
	}
}