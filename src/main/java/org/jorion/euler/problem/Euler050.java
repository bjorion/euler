package org.jorion.euler.problem;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * The prime 41, can be written as the sum of six consecutive primes:
 *
 * <pre>
 * 41 = 2 + 3 + 5 + 7 + 11 + 13
 * </pre>
 *
 * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
 * <p>
 * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to
 * 953.
 * <p>
 * Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */
public class Euler050 {

	public static void main(String[] args) {

		// 78498 primes up to 1.000.000
		final int max = 1_000_000;

		long res; // 997651 (543 consecutive prime, starting from 7)
		long delta;

		// Utils.start();
		// res = calc1(max);
		// delta = Utils.stop();
		// Utils.print("Brute Force ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Advanced              ", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Advanced + SoE (best) ", res, delta);
	}

	/**
	 * Start by finding first the prime numbers (around 1.5s)
	 */
	private static long calc2(int max) {
		int largest = 0;
		int res = 0;
		int sum, count;

		for (int i = 2; i < max; i++) {
			if (PrimeUtils.isPrime6(i)) {
				count = sum = 0;
				// starting from a prime number (2, 3...), compute a sequence of primes until the sum >= max.
				for (int x = i; sum < max; x++) {
					if (PrimeUtils.isPrime6(x)) {
						sum += x;
						count++;
						if (PrimeUtils.isPrime6(sum) && (sum < max) && (count > largest)) {
							largest = count;
							res = sum;
						}
					}
				}
			}
		}
		// System.out.println(res + " " + largest);
		return res;
	}

	private static long calc3(int max) {
		boolean[] arr = PrimeUtils.isPrimeSoE(max);
		int largest = 0;
		int res = 0;
		int sum, count;

		for (int i = 2; i < max; i++) {
			if (arr[i]) {
				count = sum = 0;
				// starting from a prime number (2, 3...), compute a sequence of primes until the sum >= max.
				for (int x = i; (x < max) && (sum < max); x++) {
					if (arr[x]) {
						sum += x;
						count++;
						if ((sum < max) && arr[sum] && (count > largest)) {
							largest = count;
							res = sum;
						}
					}
				}
			}
		}
		return res;
	}

	/**
	 * Around 50s. So bad algorithm I don't want to use it anymore.
	 */
	@SuppressWarnings("unused")
	private static long calc1(int max) {
		boolean[] arr = PrimeUtils.isPrimeSoE(max);
		int nPrime = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]) {
				nPrime++;
			}
		}
		// System.out.println(nPrime); // 78498

		long bestSum = 0;
		int bestCount = 0;
		for (int len = 2; len <= nPrime; len++) {
			int cur = 0;
			long sum = 0;
			int startCount = 0;
			int smallestIndex = 2;
			for (int i = 0; i < len; i++) {
				cur = MathUtils.nextTrue(arr, cur);
				sum += cur;
			}
			while (true) {
				if (sum >= max) {
					break;
				}
				if (arr[(int) sum]) {
					if (len > bestCount) {
						bestCount = len;
						bestSum = sum;
					}
				}
				startCount++;
				if (startCount + len > nPrime) {
					break;
				}
				// remove smallest prime
				sum -= smallestIndex;
				smallestIndex = MathUtils.nextTrue(arr, smallestIndex);

				// add new prime
				cur = MathUtils.nextTrue(arr, cur);
				sum += cur;
			}
		}

		// System.out.println("best count: " + bestCount); // 543
		return bestSum;
	}
}
