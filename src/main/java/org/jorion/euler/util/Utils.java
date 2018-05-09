package org.jorion.euler.util;

/**
 * Set of utility methods.
 */
public class Utils {
	// --- Variables ---
	private static long start;

	// --- Methods ---
	/**
	 * Start the chronometer.
	 */
	public static void start() {
		start = System.nanoTime();
	}

	/**
	 * @return the chronometer value.
	 */
	public static long stop() {
		return System.nanoTime() - start;
	}

	/**
	 * Display a formatted string.
	 *
	 * @param msg a free text
	 * @param res a numerical result
	 * @param delta the computation time (in µs)
	 */
	public static void print(String msg, long res, long delta) {
		print(msg, Long.toString(res), delta);
	}

	public static void print(String msg, String res, long delta) {
		String nanosec = String.format("%8d ns", delta);
		String time = nanosec;
		delta /= 1000;
		if (delta > 0) {
			String microsec = String.format("%8d µs", delta);
			time = microsec;
			delta /= 1000;
		}
		if (delta > 0) {
			String millisec = String.format("%8d ms", delta);
			time = millisec;
		}
		System.out.println(msg + " - Result: " + res + " - time: " + time);
	}

	/**
	 * @return True if the given number is palindromic in base 10.
	 */
	public static boolean isPalindromic(int val) {
		return isPalindromic(Integer.toString(val));
	}

	/**
	 * @return True if the given string is palindromic.
	 */
	public static boolean isPalindromic(String str) {
		int len = str.length();
		boolean flag = true;
		for (int i = 0; i < (len + 1) / 2; i++) {
			if (str.charAt(i) != str.charAt(len - i - 1)) {
				flag = false;
				break;
			}
		}
		return flag;
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
	 */
	public static boolean[] isPrimeSoE(int n) {
		// initially assume all integers are prime
		boolean[] isPrimes = new boolean[n + 1];
		for (int i = 2; i <= n; i++) {
			isPrimes[i] = true;
		}

		// mark non-primes <= n using Sieve of Eratosthenes
		for (int factor = 2; factor * factor <= n; factor++) {

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
	 * Formula: C<sup>p</sup><sub>n</sub> = n! / (p! * (n - p)!)
	 * <pre>
	 * denombrement_combinaisons( p , n ) {
	 * 	si (p = n) retourner 1;
	 * 	si (p > n/2) p = n-p;
	 * 	res = n-p+1;
	 * 	pour i = 2 par 1 tant que i < = p
	 * 	res = res * (n-p+i)/i;
	 * 	fin pour
	 * 	retourner res;
	 * </pre>
	 */
	public static long combinationCPN(int p, int n) {
		if (p == n) {
			return 1;
		}
		if (p > n / 2) {
			p = n - p;
		}
		long res = n - p + 1;
		for (int i = 2; i <= p; i++) {
			res = res * (n - p + i) / i;
		}
		return res;
	}

}
