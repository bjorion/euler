package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * <p>
 * A <b>Harshad number</b> is a number that is divisible by the sum of its digits. 201 is a Harshad number because it is divisible by 3 (the sum of
 * its digits.) When we truncate the last digit from 201, we get 20, which is a Harshad number. When we truncate the last digit from 20, we get 2,
 * which is also a Harshad number. Let's call a Harshad number that, while recursively truncating the last digit, always results in a Harshad number a
 * <b>right truncatable Harshad number</b>.
 * </p>
 * <p>
 * Also: 201 / 3 = 67 which is prime. <br>
 * Let's call a Harshad number that, when divided by the sum of its digits, results in a prime a <b>strong Harshad number</b>.
 * </p>
 * <p>
 * Now take the number 2011 which is prime. When we truncate the last digit from it we get 201, a strong Harshad number that is also right
 * truncatable. Let's call such primes <b>strong, right truncatable Harshad primes</b>.
 * </p>
 * You are given that the sum of the strong, right truncatable Harshad primes less than 10000 is 90619.
 *
 * Find the sum of the strong, right truncatable Harshad primes less than 10^14 = 100_000_000_000_000
 */
public class Euler387 {

	// --- Methods ---
	public static void main(String[] args) {

		final long max = 100_000_000_000_000L; // 10_000L;
		long res; // 696_067_597_313_468
		long delta;

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	/**
	 * This algorithm is way too slow.
	 */
	@Deprecated
	@SuppressWarnings("unused")
	private static String calc1(long max) {

		BigInteger res = BigInteger.ZERO;
		long max2 = max / 10;
		for (long n = 11; n < max2; n++) {
			String str = Long.toString(n);
			int sum = isHarshad(n, str);
			if (sum > 0) {
				if (isRTHarshad(n, str)) {
					if (isStrongHarshad(n, sum)) {
						// we have a number [abc], check if any [abc][n] is prime
						long nn1 = n * 10;
						long nn2 = nn1 + 10;
						long nn = nn1 + 1;
						while (nn < nn2) {
							if (PrimeUtils.isPrime6(nn)) {
								System.out.println("adding: " + nn);
								res = res.add(new BigInteger(Long.toString(nn)));
							}
							nn += 2;
						}
					}
				}
			}
		}
		return res.toString();
	}

	/**
	 * Use recursion to find solutions.
	 */
	private static long calc2(long max) {

		long res = 0;
		for (long i = 1; i <= 9; i++) {
			res += loop(i, max);
		}
		return res;
	}

	private static long loop(long base, long max) {

		long next = base * 10;
		long res = 0;

		// stop recurrence
		if (next * 10 >= max) {
			return res;
		}

		for (int i = 0; i <= 9; i++) {
			long curr = next + i;
			int sum = isHarshad(curr, Long.toString(curr));
			if (sum < 0) {
				continue;
			}
			if (isStrongHarshad(curr, sum)) {
				res += isStrongHarshadPrime(curr);
			}
			res += loop(curr, max);
		}
		return res;
	}

	/**
	 * @param base a Strong Harshad number
	 * @return the sum of strong Harshad number primes
	 */
	private static long isStrongHarshadPrime(long base) {

		long res = 0;
		long next = base * 10;
		// we skip even numbers
		for (int i = 1; i <= 9; i = i + 2) {
			long curr = next + i;
			if (PrimeUtils.isPrime6(curr)) {
				// System.out.println("adding: " + curr);
				res += curr;
			}
		}
		return res;
	}

	/**
	 * @param n the number to test
	 * @param str a string representation of this number
	 * @return the sum of the digits of n, if n is divisable by this sum, -1 otherwise
	 */
	private static int isHarshad(long n, String str) {

		int sum = WordUtils.countDigits(str);
		return (n % sum == 0) ? sum : -1;
	}

	/**
	 * @return true if (n / sum) is a prime
	 */
	private static boolean isStrongHarshad(long n, int sum) {

		long div = n / sum;
		return PrimeUtils.isPrime6(div);
	}

	/**
	 * Right-truncate every digit of the given number and returns true if every result turns out to be a Harshad number.
	 */
	@Deprecated
	private static boolean isRTHarshad(long n, String str) {

		boolean rtHarshad = false;
		int len = str.length();
		long n2 = n;
		for (int i = 1; i < len; i++) {
			n2 /= 10;
			rtHarshad = (isHarshad(n2, str.substring(0, len - i)) > 0);
			if (!rtHarshad) {
				break;
			}
		}
		return rtHarshad;
	}
}