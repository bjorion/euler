package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * <p>
 * What is the largest prime factor of the number 600_851_475_143 ?
 */
public class Euler003 {

	public static void main(String[] args) {

		final long max = 600_851_475_143L;

		long res; // 6857
		long start, delta;

		start = System.nanoTime();
		res = calc1(max);
		delta = System.nanoTime() - start;
		Utils.print("Compute primes (heavy)", res, delta);

		start = System.nanoTime();
		res = calc2(max);
		delta = System.nanoTime() - start;
		Utils.print("Without primes (best) ", res, delta);
	}

	/**
	 * Solution using a routine to find primes.
	 */
	static long calc1(final long max) {

		long high = max;
		long prime = 2;
		long res = 0;

		while (true) {
			if (prime > high) {
				break;
			}
			if (high % prime == 0) {
				// System.out.println("found: " + prime);
				res = prime;
				high = high / prime;
				continue;
			}
			if (prime == 2) {
				prime = 3;
				continue;
			}
			while (true) {
				prime += 2;
				if (Utils.isPrime(prime)) {
					break;
				}
			}
		}
		return res;
	}

	/**
	 * Solution without primes.
	 */
	static long calc2(final long max) {

		long high = max;
		long val = 2;

		while (high > 1) {
			if (high % val == 0) {
				// System.out.println("found: " + val);
				high = high / val;
				continue;
			}
			val++;
		}

		return val;
	}

}
