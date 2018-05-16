package org.jorion.euler.util;

public class PrimeUtils {

	// --- Methods ---
	static void wakeUp() {
	}

	/**
	 * Find if the given number is prime. We test all the odd numbers as divisors.
	 *
	 * @param n the value to test
	 * @return True if the given value is prime, false otherwise
	 */
	public static boolean isPrime(long n) {
		if (n == 2 || n == 3) {
			return true;
		}
		if (n % 2 == 0 || n < 2) {
			return false;
		}

		boolean result = true;
		final long sqrt = (long) Math.sqrt(n);
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

	/**
	 * Find if the given number is prime. We test all the multiples of 6 +/- 1 as divisors (all primes, except 3,
	 * satisfy the condition 6n+/-1)
	 * <p>
	 * This algorithm should be more performant than <code>isPrime(n)</code> (we check 33% of the numbers instead of
	 * 50%).
	 *
	 * @param n the value to test
	 * @return True if the given value is prime, false otherwise
	 */
	public static boolean isPrime6(long n) {
		if (n == 2 || n == 3) {
			return true;
		}
		if (n % 2 == 0 || n % 3 == 0 || n < 2) {
			return false;
		}

		boolean done = false;
		boolean result = true;
		long sqrt = (long) Math.sqrt(n);
		long k = 0;

		while (!done) {
			k++;
			for (int i = -1; i <= 1; i = i + 2) {
				long val = k * 6 + i;
				if (val <= sqrt) {
					if (n % val == 0) {
						result = false;
						done = true;
						break;
					}
				} else {
					done = true;
				}
			}
		}
		return result;
	}

	/**
	 * Use the Sieve of Eratosthenes to find all primes between 1 and n.
	 *
	 * @param n the upper limit for the sieve
	 * @return an array of booleans, true for a prime
	 */
	public static boolean[] isPrimeSoE(int n) {
		// initially assume all integers are prime
		boolean[] isPrimes = new boolean[n + 1];
		isPrimes[2] = true;
		for (int i = 3; i <= n; i++) {
			// for even numbers: false; for odd numbers: true
			isPrimes[i] = ((i & 0b1) == 1);
		}

		// mark non-primes <= n using Sieve of Eratosthenes
		for (int factor = 3; factor * factor <= n; factor++) {

			// if factor is prime, then mark multiples of factor as nonprime
			// suffices to consider mutiples factor, factor+1, ..., n/factor
			if (isPrimes[factor]) {
				for (int j = factor; factor * j <= n; j++) {
					isPrimes[factor * j] = false;
				}
			}
		}
		return isPrimes;
	}

	/**
	 * Given an array of booleans (provided by {@link #isPrimeSoE(int)}), returns the index of the next prime in the
	 * array.
	 *
	 * @param arr an array of booleans
	 * @param index the current index
	 * @return the index of the next prime (greater than the given index)
	 */
	public static int nextPrime(boolean[] arr, int index) {
		while (index < arr.length) {
			index++;
			if (arr[index]) {
				break;
			}
		}
		return index;
	}
}
