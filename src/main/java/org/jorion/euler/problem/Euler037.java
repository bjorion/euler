package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
 * <p>
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 * <p>
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class Euler037 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 11;
		long res; // 748317
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple ", res, delta);
	}

	private static long calc1(int max) {

		int sum = 0;
		int count = 0;
		int n = 21;
		// primes: 2,3,5,7
		boolean[] firstDigitPrimes = { false, false, true, true, false, true, false, true, false, false };
		// number ending with 2 and 5 can not be prime
		boolean[] lastDigitPrimes = { false, false, false, true, false, false, false, true, false, false };

		while (count < max) {
			n += 2;
			// check last digit
			if (!lastDigitPrimes[n % 10]) {
				continue;
			}
			// check first digit
			String str = Integer.toString(n);
			if (!firstDigitPrimes[str.charAt(0) - '0']) {
				continue;
			}
			// check whole number
			if (!PrimeUtils.isPrime6(n)) {
				continue;
			}

			boolean prime = true;
			// remove digits from left to right
			for (int i = 1; i < str.length() - 1; i++) {
				int m = Integer.parseInt(str.substring(i));
				if (!PrimeUtils.isPrime6(m)) {
					prime = false;
					break;
				}
			}
			if (!prime) {
				continue;
			}

			// remove digits from right to left
			for (int i = str.length() - 1; i > 1; i--) {
				int m = Integer.parseInt(str.substring(0, i));
				if (!PrimeUtils.isPrime6(m)) {
					prime = false;
					break;
				}
			}
			if (!prime) {
				continue;
			}
			count++;
			sum += n;
		}
		return sum;
	}

}