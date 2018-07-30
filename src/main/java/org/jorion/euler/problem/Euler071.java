package org.jorion.euler.problem;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * Consider the fraction, n/d, where n and d are positive integers. If n &lt; d and HCF(n,d)=1, it is called a reduced proper fraction.
 * <p>
 * If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
 * <p>
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * <p>
 * It can be seen that 2/5 is the fraction immediately to the left of 3/7.
 * <p>
 * By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the
 * left of 3/7 (0.428571...428571...) (greater than 2/5 = 0.40)
 */
public class Euler071 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 1_000_000;
		long res; // 428570
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	private static long calc1(int max) {

		// int count = 0;
		int[] res = { 2, 5 };
		for (int n = 1; n < max; n++) {
			int d0 = 7 * n / 3;
			for (int d = d0; d <= max; d++) {
				// count++;
				// we ignore fractions > 3/7
				if (isSmallerThan37(n, d)) {
					if (isLargerThanX(n, d, res)) {
						if (isHCFEqualTo1(n, d)) {
							res[0] = n;
							res[1] = d;
						}
					}
					// we stop the second loop if we are too "low"
					else {
						break;
					}
				}
			}
		}
		// System.out.println(count + ": " + res[0] + "/" + res[1]);
		return res[0];
	}

	private static boolean isHCFEqualTo1(int n, int d) {

		boolean flag = true;
		if (n % 2 == 0 && d % 2 == 0) {
			flag = false;
		}
		else if (MathUtils.gcd(n, d) > 1) {
			flag = false;
		}
		return flag;
	}

	private static boolean isLargerThanX(int n, int d, int[] x) {

		return (x[0] * d < x[1] * n);
	}

	private static boolean isSmallerThan37(int n, int d) {

		return (7 * n < 3 * d);
	}
}