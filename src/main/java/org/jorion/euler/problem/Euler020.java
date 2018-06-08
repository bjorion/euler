package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.Utils;

/**
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * <p>
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800, <br>
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * <p>
 * Find the sum of the digits in the number 100!
 */
public class Euler020 {

	// --- Constants ---
	private static final int ASCII_0 = 48;

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 100;
		long res; // 648
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Brute force ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Simple      ", res, delta);
	}

	private static long calc1(int max) {

		int sum = 0;
		BigInteger p = BigInteger.ONE;
		BigInteger ten = new BigInteger("10");
		for (int i = 2; i <= max; i++) {
			p = p.multiply(new BigInteger(Integer.toString(i)));
			while (p.mod(ten) == BigInteger.ZERO) {
				p = p.divide(ten);
				// System.out.println("reduction - p = " + p + ", i = " + i);
			}
		}
		String str = p.toString();
		// System.out.println(str);
		for (char ch : str.toCharArray()) {
			sum += (ch - ASCII_0);
		}
		return sum;
	}

	@SuppressWarnings("unused")
	private static long addDigits(long n) {

		long res = 0;
		while (n > 0) {
			res += (n % 10);
			n /= 10;
		}
		return res;
	}

	private static long calc2(int max) {

		final int size = 200;
		int[] digits = new int[size];
		digits[0] = 1;

		int maxIndex = 1;
		for (int i = 2; i <= max; i++) {
			int n = i;

			// avoid unnecessary multiplication by 10
			while (n % 10 == 0) {
				n /= 10;
			}
			if (n == 1) {
				continue;
			}

			digits[0] = digits[0] * n;
			for (int k = 1; k < maxIndex + 2; k++) {
				digits[k] = digits[k] * n + digits[k - 1] / 10;
			}
			for (int k = 0; k < maxIndex + 2; k++) {
				digits[k] = digits[k] % 10;
			}
			maxIndex += 2;
			while (digits[maxIndex - 1] == 0) {
				maxIndex--;
			}
		}

		// System.out.println(WordUtils.arrayToString(digits) + ", maxIndex: " + maxIndex);
		int sum = 0;
		for (int i = 0; i < maxIndex; i++) {
			sum += digits[i];
		}
		return sum;
	}

}
