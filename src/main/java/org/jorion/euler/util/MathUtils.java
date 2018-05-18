package org.jorion.euler.util;

import java.util.HashMap;
import java.util.Map;

public class MathUtils {

	//--- Methods ---
	protected static void wakeUp() {
	}

	/**
	 * Formula: C<sup>p</sup><sub>n</sub> = n! / (p! * (n - p)!)
	 *
	 * @param p the small number (0 &le; p &le; n)
	 * @param n the large number
	 * @return the computed value
	 */
	public static long cpn(int p, int n) {
		if (p == 0 || p == n) {
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

	/**
	 * Find the Greatest Common Divisor (gcd) between two integers.
	 *
	 * @param a the first integer
	 * @param b the second integer
	 * @return the gcd between a and b
	 * @see https://en.wikipedia.org/wiki/Euclidean_algorithm
	 */
	public static int gcd(int a, int b) {
		int remainder;
		while (b != 0) {
			remainder = a % b;
			a = b;
			b = remainder;
		}
		return a;
	}

	/**
	 * Given an array of booleans, returns the index of the next "true" in the
	 * array.
	 *
	 * @param arr an array of booleans
	 * @param index the current index
	 * @return the index of the next true (greater than the given index)
	 */
	public static int nextTrue(boolean[] arr, int index) {
		int len = arr.length;
		while (index < len) {
			index++;
			if (arr[index]) {
				break;
			}
		}
		return (index < len) ? index : -1;
	}

	/**
	 * Find all the prime factors of the given integer.
	 *
	 * @param n the integer
	 * @return A map with key = prime factor and value = number of occurrences (>= 1)
	 */
	public static Map<Integer, Integer> findPrimeFactors(int n) {
		Map<Integer, Integer> map = new HashMap<>();
		int div = 2;
		while (n > 1) {
			int count = 0;
			while (n % div == 0) {
				n = n / div;
				count++;
			}
			if (count > 0) {
				map.put(div, count);
			}
			div = (div == 2) ? 3 : div + 2;
		}
		return map;
	}

	/**
	 * Alternative to {@link #findPrimeFactors(int)} when we already know the primes up to n.
	 *
	 * @param n the integer
	 * @param primes an array of boolean, value of index 'n' is true if 'n' is a prime
	 * @return A map with key = prime factor and value = number of occurrences (>= 1)
	 */
	public static Map<Integer, Integer> findPrimeFactors(int n, boolean[] primes) {
		boolean stopPrime = false;
		Map<Integer, Integer> map = new HashMap<>();
		int div = 2;
		while (n > 1) {
			int count = 0;
			while (n % div == 0) {
				n = n / div;
				count++;
			}
			if (count > 0) {
				map.put(div, count);
			}
			if (!stopPrime) {
				int next = nextTrue(primes, div);
				if (next < 0) {
					System.out.println("WARN: primes[] to small");
					stopPrime = true;
					div += 2;
				} else {
					div = next;
				}
			} else {
				div += 2;
			}
		}
		return map;
	}
}
