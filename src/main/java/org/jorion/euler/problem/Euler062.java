package org.jorion.euler.problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * <p>
 * The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest
 * cube which has exactly three permutations of its digits which are also cube.
 * <p>
 * Find the smallest cube for which exactly five permutations of its digits are cube.
 */
public class Euler062 {

	// --- Variables ---

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 5;
		String res; // 127035954683
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple ", res, delta); // 1200ms

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Best   ", res, delta); // 7ms
	}

	/**
	 * Here we precompute the cubes and for each cube, we check how many other cubes (strictly greater) are permutations of the current one.
	 */
	private static String calc1(int max) {

		int start = 345;
		int end = 9999;

		Map<Integer, String> map = new HashMap<>();
		for (int i = start; i <= end; i++) {
			long cube = ((long) i) * i * i;
			map.put(i, Long.toString(cube));
		}

		int best = 1;
		String res = null;

		for (int i = start; i <= end; i++) {
			String cube = map.get(i);
			int count = 1;
			for (int j = i + 1; j <= end; j++) {
				String str = map.get(j);
				// if "str" is longer than "cube" then no need to go further (since they can't be permutations of each other anymore)
				if (str.length() > cube.length()) {
					break;
				}
				if (WordUtils.isPermutation(cube, str)) {
					count++;
				}
			}
			if (count > best) {
				best = count;
				res = cube;
				// System.out.println("best: " + best + ", res: " + res);
				// as soon as we find the requested number, we stop here
				if (best == max) {
					break;
				}
			}
		}
		return res;
	}

	/**
	 * Improvement: we avoid checking the permutation my ordering the string at the creation.
	 */
	private static String calc2(int max) {

		int start = 345;
		int end = 9999;

		String res = "0";
		Map<String, Dual> map = new HashMap<>();
		for (int i = start; i <= end; i++) {
			long val = ((long) i) * i * i;
			String cube = Long.toString(val);
			char[] arr = cube.toCharArray();
			Arrays.sort(arr);
			String key = new String(arr);
			Dual dual = map.getOrDefault(key, new Dual(0, cube));
			dual = new Dual(dual.count + 1, dual.base);
			map.put(key, dual);
			if (dual.count == max) {
				res = dual.base;
				break;
			}
		}

		return res;
	}

	static class Dual {

		final int count;

		final String base;

		public Dual(int count, String base) {

			this.count = count;
			this.base = base;
		}
	}
}
