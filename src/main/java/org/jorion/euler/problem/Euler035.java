package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
 * <p>
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 * <p>
 * How many circular primes are there below one million?
 */
public class Euler035 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 1_000_000;
		long res; // 55
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple      ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Use Log+Pow ", res, delta);

		Utils.start();
		res = calc2b(max);
		delta = Utils.stop();
		Utils.print("Use Mul     ", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("SoE         ", res, delta);
	}

	/**
	 * Use String to rotate the number.
	 */
	private static long calc1(int max) {

		int sum = 4; // 2,3,5,7
		for (int n = 11; n <= max; n = n + 2) {
			if (PrimeUtils.isPrime6(n)) {
				boolean ok = true;
				StringBuilder sb = new StringBuilder(Integer.toString(n));
				int len = sb.length();
				for (int i = 0; i < sb.length() - 1; i++) {
					char ch = sb.charAt(0);
					sb.delete(0, 1);
					sb.append(ch);
					int p = Integer.parseInt(sb.toString());
					// if p < n, we already checked p, so we stop here
					if (p < n) {
						ok = false;
						break;
					}
					if (p == n) {
						len--;
					}
					// case when p > n
					else if (!PrimeUtils.isPrime6(p)) {
						ok = false;
						break;
					}
				}
				if (ok) {
					// System.out.println("ok for " + n);
					sum += len;
				}
			}
		}
		return sum;
	}

	/**
	 * Use Math to rotate the number.
	 */
	private static long calc2(int max) {

		int sum = 4; // 2,3,5,7
		for (int n = 11; n <= max; n = n + 2) {
			if (PrimeUtils.isPrime6(n)) {
				boolean ok = true;
				int p = n;
				int len = ((int) Math.log10(n)) + 1;
				for (int i = 0; i < len - 1; i++) {
					int digit = p % 10;
					p = p / 10;
					p = (digit * ((int) Math.pow(10, len - 1))) + p;
					if (p < n) {
						ok = false;
						break;
					}
					if (p == n) {
						len--;
					} else if (!PrimeUtils.isPrime6(p)) {
						ok = false;
						break;
					}
				}

				if (ok) {
					// System.out.println("ok for " + n);
					sum += len;
				}
			}
		}
		return sum;
	}

	/**
	 * Use Math to rotate the number.
	 */
	private static long calc2b(int max) {

		int sum = 4; // 2,3,5,7
		for (int n = 11; n <= max; n = n + 2) {
			if (PrimeUtils.isPrime6(n)) {
				boolean ok = true;
				int p = n;
				int m = n;
				int len = 0;
				int c = 1;
				while (m > 0) {
					m /= 10;
					len++;
					c = c * 10;
				}
				c /= 10;
				for (int i = 0; i < len - 1; i++) {
					int digit = p % 10;
					p = p / 10;
					p = (digit * c) + p;
					if (p < n) {
						ok = false;
						break;
					}
					if (p == n) {
						len--;
					} else if (!PrimeUtils.isPrime6(p)) {
						ok = false;
						break;
					}
				}

				if (ok) {
					// System.out.println("ok for " + n);
					sum += len;
				}
			}
		}
		return sum;
	}

	/**
	 * Use Sieve Of Eratosthenes.
	 */
	private static long calc3(int max) {

		int sum = 4;
		boolean[] primes = PrimeUtils.isPrimeSoE(max);
		for (int n = 11; n <= max; n = n + 2) {
			if (primes[n]) {
				boolean ok = true;
				int p = n;
				int len = ((int) Math.log10(n)) + 1;
				for (int i = 0; i < len - 1; i++) {
					int digit = p % 10;
					p = p / 10;
					p = (digit * ((int) Math.pow(10, len - 1))) + p;
					if (p < n) {
						ok = false;
						break;
					}
					if (p == n) {
						len--;
					} else if (!primes[p]) {
						ok = false;
						break;
					}
				}

				if (ok) {
					// System.out.println("ok for " + n);
					sum += len;
				}
			}
		}
		return sum;
	}

}
