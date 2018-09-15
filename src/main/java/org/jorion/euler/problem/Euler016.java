package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * <p>
 * What is the sum of the digits of the number 2^1000?
 */
public class Euler016 {

	// --- Constants ---
	private static final int ASCII_0 = 48;

	//--- Methods ---
	public static void main(String[] args) {

		final int max = 1000;
		long res; // 1366
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("BigInteger mul", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("BigInteger pow", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Array of int  ", res, delta);
	}

	private static int calc1(int max) {

		BigInteger n = BigInteger.ONE;
		for (int i = 0; i < max; i++) {
			n = n.multiply(MathUtils.TWO);
		}

		int sum = 0;
		String str = n.toString();
		for (int i = 0; i < str.length(); i++) {
			sum += str.charAt(i);
		}
		sum -= ASCII_0 * str.length();
		return sum;
	}

	private static int calc2(int max) {

		BigInteger n = MathUtils.TWO.pow(max);

		int sum = 0;
		String str = n.toString();
		for (int i = 0; i < str.length(); i++) {
			sum += str.charAt(i);
		}
		sum -= ASCII_0 * str.length();
		return sum;
	}

	private static long calc3(int max) {

		final int size = 500;
		int[] arr = new int[size];
		int sum = 0;
		arr[0] = 1;

		int maxIndex = 1;
		for (int i = 1; i <= max; i++) {
			arr[0] = 2 * arr[0];
			for (int k = 1; k < maxIndex + 1; k++) {
				arr[k] = 2 * arr[k] + arr[k - 1] / 10;
			}
			for (int k = 0; k < maxIndex + 1; k++) {
				if (arr[k] >= 10) {
					// same than "arr[k] % 10"
					arr[k] = arr[k] - 10;
				}
			}
			if (arr[maxIndex] > 0) {
				maxIndex++;
			}
		}
		// System.out.println("maxIndex: " + maxIndex);
		for (int i = 0; i < maxIndex; i++) {
			sum += arr[i];
		}
		return sum;
	}

}
