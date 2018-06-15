package org.jorion.euler.problem;

import java.util.HashSet;
import java.util.Set;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * It was proposed by Christian Goldbach that every odd <b>composite</b> number can be written as the sum of a prime and twice a square.
 * <pre>
 *  9 =  7 + 2×1^2
 * 15 =  7 + 2×2^2
 * 21 =  3 + 2×3^2
 * 25 =  7 + 2×3^2
 * 27 = 19 + 2×2^2
 * 33 = 31 + 2×1^2
 * 35 = 17 + 2*3^2
 * </pre>
 * It turns out that the conjecture was false.
 *
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 */
public class Euler046 {

	// --- Methods ---
	public static void main(String[] args) {

		long res; // 5777
		long delta;

		Utils.start();
		res = calc1();
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	private static long calc1() {

		Set<Integer> primes = new HashSet<>();
		// primes.add(2);
		primes.add(3);
		primes.add(5);
		primes.add(7);
		int res = 0;

		int n = 7;
		while (true) {
			n += 2;
			// find primes
			if (PrimeUtils.isPrime6(n)) {
				primes.add(n);
				continue;
			}
			// here we have an odd composite number
			// check if it can be written as the sum of a prime and twice a square
			boolean found = false;
			for (int p : primes) {
				// n is odd, p must be odd so (n-p) must be even
				int val = (n - p) / 2;
				double sqrt = Math.sqrt(val);
				if (Math.floor(sqrt) == sqrt) {
					found = true;
					break;
				}
			}
			if (!found) {
				res = n;
				break;
			}
		}
		return res;
	}

}