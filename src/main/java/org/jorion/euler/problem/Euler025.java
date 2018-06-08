package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * The Fibonacci sequence is defined by the recurrence relation:
 * <pre>
 *     Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
 * </pre>
 * Hence the first 12 terms will be:
 * <pre>
 *     F1 = 1
 *     F2 = 1
 *     F3 = 2
 *     F4 = 3
 *     F5 = 5
 *     F6 = 8
 *     F7 = 13
 *     F8 = 21
 *     F9 = 34
 *     F10 = 55
 *     F11 = 89
 *     F12 = 144
 * </pre>
 * The 12th term, F12, is the first term to contain three digits.
 * <p>
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 */
public class Euler025 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 1000;
		long res; // 4782
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple ", res, delta);
	}

	private static long calc1(int max) {

		final int size = 1000;

		int index = 2, length = 1;
		int[] f1 = new int[size + 1];
		int[] f2 = new int[size + 1];
		int[] fa, fb;

		f1[0] = 1;
		f2[0] = 1;
		fa = f1;
		fb = f2;

		while (length < max) {
			index++;
			int report = 0;
			for (int i = 0; i < length; i++) {
				fb[i] += fa[i] + report;
				report = fb[i] / 10;
				fb[i] = fb[i] % 10;
			}
			if (report > 0) {
				fb[length++] = report;
			}
			int[] ft = fa;
			fa = fb;
			fb = ft;
		}
		// System.out.println(Arrays.toString(fa));
		return index;
	}

}