package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
 * <p>
 * How many n-digit positive integers exist which are also an nth power?
 */
public class Euler063 {

	// --- Methods ---
	public static void main(String[] args) {

		long res;
		long delta;

		Utils.start();
		res = calc1();
		delta = Utils.stop();
		Utils.print("Simple ", res, delta);
	}

	private static int calc1() {

		int res = 0;
		boolean done = false;
		int exp = 0;
		int min = 0;
		while (!done) {
			exp++;
			for (int i = min; i < 10; i++) {
				done = true;
				String str = Long.toString(exp(i, exp));
				if (str.length() == exp) {
					min = i;
					res += (10 - i);
					done = false;
					break;
				}
			}
		}
		return res;
	}

	private static long exp(int n, int exp) {

		long val = 1;
		for (int i = 0; i < exp; i++) {
			val *= n;
		}
		return val;
	}

}