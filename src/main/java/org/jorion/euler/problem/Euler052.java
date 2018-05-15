package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.
 * <p>
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */
public class Euler052 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 6;
		long res; // 142857 (= period of 1/7)
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple", res, delta);
	}

	private static long calc1(int max) {

		int res;
		int n = 10;
		while (true) {
			int[] hash = hash(n);
			int tmp = n;
			boolean found = true;
			for (int i = 1; i < max; i++) {
				tmp += n;
				int[] hashTmp = hash(tmp);
				if (!hashEquals(hash, hashTmp)) {
					found = false;
					break;
				}
			}
			if (found) {
				res = n;
				// IntStream.rangeClosed(1, max).forEach(x -> {
				//    System.out.println(res * x);
				// });
				break;
			}
			n++;
		}
		return res;
	}

	/**
	 * Convert a number into an array of digits.
	 */
	private static int[] hash(int n) {

		int[] hash = new int[10];
		while (n > 0) {
			int val = n % 10;
			hash[val] = hash[val] + 1;
			n /= 10;
		}
		return hash;
	}

	/**
	 * @return true if the two given arrays are equal.
	 */
	private static boolean hashEquals(int[] a, int[] b) {

		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			if (a[i] != b[i]) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
