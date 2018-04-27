package org.jorion.euler.problem;

import java.util.HashMap;
import java.util.Map;

import org.jorion.euler.util.Utils;

/**
 * The following iterative sequence is defined for the set of positive integers:
 *<pre>
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 *</pre>
 * Using the rule above and starting with 13, we generate the following sequence:
 * <pre>
 * 13 → 40 → 20 → 10 →  5 → 16 →  8 →  4 →  2 →  1
 *
 *  3 → 10 →  5 → 16 →  8 →  4 →  2 →  1 (len: 7 operations)
 *  7 → 22 → 11 → 34 → 17 → 52 → 26 → 13 → 40 → 20 → 10 →  5 → 16 →  8 →  4 →  2 →  1
 *  9 → 28 → 14 →  7...
 *</pre>
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 *
 * Which starting number, under one million, produces the longest chain?
 *<p>
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 *
 */
public class Euler014 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 1_000_000;
		long res; // 837799 (524 operations)
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Brute force ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Simple Map  ", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Simple Map 2", res, delta);

		Utils.start();
		res = calc4(max);
		delta = Utils.stop();
		Utils.print("Array (Best)", res, delta);
	}

	/**
	 * Brute force.
	 */
	private static long calc1(int max) {

		int longest = 0;
		int terms = 0;
		int i;
		long j;

		for (i = 1; i <= max; i++) {
			j = i;
			int this_terms = 1;
			while (j != 1) {
				this_terms++;
				j = next(j);
			}
			if (this_terms > terms) {
				terms = this_terms;
				longest = i;
			}
		}
		return longest;
	}

	/**
	 * We build a map with key = starting value (ex: 13) and value = number of operations (9).
	 */
	private static long calc2(long max) {

		Map<Long, Integer> map = new HashMap<>();
		map.put(2L, 1);
		long best = 0;
		int nOp = 0;

		for (long i = 3; i < max; i++) {
			long j = i;
			int count = 1;
			while (true) {
				j = next(j);
				if (map.containsKey(j)) {
					int nbr = map.get(j) + count;
					map.put(i, nbr);
					// System.out.println(i + " => " + map.get(i) + ", count: " + count);
					if (nbr > nOp) {
						best = i;
						nOp = nbr;
					}
					break;
				}
				count++;
			}
		}
		// System.out.println("Solution: " + best + ", # ops: " + map.get(best));
		// System.out.println("total # ops: " + nOps);
		return best;
	}

	/**
	 * We build a map with key = starting value (ex: 13) and value = number of operations (9).
	 * AND we prefil this map with the powers of 2.
	 */
	private static long calc3(long max) {

		Map<Long, Integer> map = new HashMap<>();
		long best = 0;
		int nOp = 0;

		// prefill with powers of two
		int po2 = (int) (Math.log(max) / Math.log(2)) + 1;
		long val = 2;
		for (int i = 1; i <= po2; i++) {
			map.put(val, i);
			val *= 2;
		}

		for (long i = 3; i < max; i++) {
			long j = i;
			int count = 1;
			while (true) {
				j = next(j);
				if (map.containsKey(j)) {
					int nbr = map.get(j) + count;
					map.put(i, nbr);
					// System.out.println(i + " => " + map.get(i) + ", count: " + count);
					if (nbr > nOp) {
						best = i;
						nOp = nbr;
					}
					break;
				}
				count++;
			}
		}
		// System.out.println("Solution: " + best + ", # ops: " + map.get(best));
		// System.out.println("total # ops: " + nOps);
		return best;
	}

	private static long calc4(int max) {

		int[] array = new int[max * 2];
		long best = 0;
		int nOp = 0;

		// prefill with powers of two
		int po2 = (int) (Math.log(max) / Math.log(2)) + 1;
		int val = 2;
		for (int i = 1; i <= po2; i++) {
			array[val] = i;
			val *= 2;
		}

		for (long i = 3; i < max; i++) {
			long j = i;
			int count = 1;
			while (true) {
				j = next(j);
				if (j < max && array[(int) j] > 0) {
					int nbr = array[(int) j] + count;
					array[(int) i] = nbr;
					// System.out.println(i + " => " + map.get(i) + ", count: " + count);
					if (nbr > nOp) {
						best = i;
						nOp = nbr;
					}
					break;
				}
				count++;
			}
		}

		return best;
	}

	private static long next(long n) {

		return ((n & 0b1) == 0) ? n >> 1 : 3 * n + 1;
	}
}
