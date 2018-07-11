package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.Utils;

/**
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 * <p>
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
public class Euler048 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 1000;
		String res; // 9110846700
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Brute Force ", res, delta);
	}

	private static String calc1(int max) {

		BigInteger sum = BigInteger.ZERO;
		for (int i = 1; i <= max; i++) {
			if (i % 10 == 0) {
				continue;
			}
			BigInteger val = new BigInteger(Integer.toString(i));
			val = val.pow(i);
			sum = sum.add(val);
		}
		String res = sum.toString();
		// System.out.println("Length:" + res.length());
		return res.substring(res.length() - 10);
	}

}