package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.List;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 *
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * <p>
 * Find the sum of all the primes below two millions.
 */
public class Euler010 {

	public static void main(String[] args) {

		final int max = 2_000_000;

		long res = 0; // 142913828922
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Brute force    ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Brute force (6)", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Eratos. (Best) ", res, delta);

		Utils.start();
		res = calc4(10000);
		delta = Utils.stop();
		Utils.print("Worse Ever     ", res, delta);
	}

	private static long calc1(int max) {

		long sum = 2;
		for (int i = 3; i < max; i = i + 2) {
			if (PrimeUtils.isPrime(i)) {
				sum += i;
			}
		}
		return sum;
	}

	private static long calc2(int max) {

		long sum = 2;
		for (int i = 3; i < max; i = i + 2) {
			if (PrimeUtils.isPrime6(i)) {
				sum += i;
			}
		}
		return sum;
	}

	private static long calc3(int max) {

		boolean res[] = PrimeUtils.isPrimeSoE(max);
		long sum = 0;
		for (int i = 0; i < res.length; i++) {
			if (res[i]) {
				sum += i;
			}
		}
		return sum;
	}

	private static long calc4(int max) {

		List<int[]> list = new ArrayList<>();
		list.add(new int[] { 2, 4 });
		list.add(new int[] { 3, 6 });

		for (int i = 4; i <= max; i++) {
			boolean found = false;
			for (int[] elem : list) {
				if (i == elem[1]) {
					found = true;
					elem[1] = elem[1] + elem[0];
				}
			}
			if (!found) {
				list.add(new int[] { i, 2 * i });
			}
		}

		long sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i)[0];
		}
		return sum;
	}
}
