package org.jorion.euler.problem;

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
		long res; // 127035954683, 1272ms
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple ", res, delta);
	}

	/**
	 * Here we precompute the cubes and for each cube, we check how many other cubes (strictly greater) are permutations of the current one.
	 */
	private static long calc1(int max) {

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
		return Long.parseLong(res);
	}

}
