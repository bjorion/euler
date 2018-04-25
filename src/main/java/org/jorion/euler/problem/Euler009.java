package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.List;

import org.jorion.euler.util.Utils;

/**
 *
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * <p>
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2 (and 3 + 4 + 5 = 12)
 * <p>
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class Euler009 {

	static List<int[]> triplets = new ArrayList<>();

	static {
		triplets.add(new int[] { 3, 4, 5 });
		triplets.add(new int[] { 5, 12, 13 });
		triplets.add(new int[] { 7, 24, 25 });
		triplets.add(new int[] { 15, 8, 17 });
		triplets.add(new int[] { 21, 20, 29 });
		triplets.add(new int[] { 55, 48, 73 });
		triplets.add(new int[] { 45, 28, 53 });
		triplets.add(new int[] { 39, 80, 89 });
		triplets.add(new int[] { 119, 120, 169 });
		triplets.add(new int[] { 77, 36, 85 });
		triplets.add(new int[] { 33, 56, 65 });
		triplets.add(new int[] { 65, 72, 97 });
		triplets.add(new int[] { 35, 12, 37 });
	}

	/**
	 * Famous triplets:
	 * <ul>
	 * <li>3, 4, 5; S=12
	 * <li>5, 12, 13; S=30
	 * </ul>
	 * @see http://mathworld.wolfram.com/PythagoreanTriple.html
	 */
	public static void main(String[] args) {

		final int max = 1000;

		long res;
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Built-in list of pythagorian triplets", res, delta);
	}

	private static long calc1(int max) {

		int index = 0;
		int k = 0;
		int res = 0;
		for (int[] triplet : triplets) {
			int sum = triplet[0] + triplet[1] + triplet[2];
			if (max % sum == 0) {
				k = max / sum;
				res = k * k * k * triplet[0] * triplet[1] * triplet[2];
				break;
			}
			index++;
		}
		int[] sol = triplets.get(index);
		System.out.println(String.format("[%d %d %d], k: %d", sol[0], sol[1], sol[2], k));
		return res;
	}
}
