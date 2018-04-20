package org.jorion.euler.util;

/**
 * Set of utility methods.
 */
public class Utils {

	/**
	 * Display a formatted string.
	 *
	 * @param msg a free text
	 * @param res a numerical result
	 * @param delta the computation time (in µs)
	 */
	public static void print(String msg, long res, long delta) {

		String time = String.format("%6d µs", delta / 1000);
		System.out.println(msg + " - Result: " + res + " - time: " + time);
	}

	/**
	 * @param n the value to test
	 * @return True if the given value is prime, false otherwise
	 */
	public static boolean isPrime(long n) {

		if (n % 2 == 0 || n < 2) {
			return false;
		}

		boolean result = true;
		long sqrt = (long) Math.sqrt(n);
		long a = 3;
		while (a <= sqrt) {
			if (n % a == 0) {
				result = false;
				break;
			}
			a += 2;
		}
		return result;
	}
}
