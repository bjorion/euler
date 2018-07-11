package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.Utils;

/**
 * It is possible to show that the square root of two can be expressed as an infinite continued fraction.
 * <pre>
 * √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.4142135623730950...
 *</pre>
 * By expanding this for the first four iterations, we get:
 * <pre>
 * 1 + 1/2 = 3/2 = 1.5
 * 1 + 1/(2 + 1/2) = 7/5 = 1.4
 * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
 * </pre>
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985,
 * is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.
 * <p>
 * In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
 */
public class Euler057 {

	// --- Constants ---
	private static final BigInteger TWO = new BigInteger("2");

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 1000;
		int res; // 153
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	private static int calc1(int max) {

		int res = 0;
		BigInteger[] fr = new BigInteger[] { TWO, BigInteger.ONE };
		for (int i = 0; i < max; i++) {
			fr = compute(fr);
			String num = fr[0].subtract(fr[1]).toString();
			String den = fr[1].toString();
			if (num.length() > den.length()) {
				res++;
			}
		}
		// System.out.println(fr[0].subtract(fr[1]).toString() + " / " + fr[1].toString());
		return res;
	}

	/**
	 * Given the fraction a/b (int[0] = a; int[1] = b), compute the fraction 2 + 1/(a/b) = (2a + b) / a
	 *
	 * @return int[0] =  (2a + b), int[1] = a
	 */
	private static BigInteger[] compute(BigInteger[] fr) {

		BigInteger[] res = new BigInteger[2];
		res[0] = fr[0].multiply(TWO).add(fr[1]);
		res[1] = fr[0];
		return res;
	}

}