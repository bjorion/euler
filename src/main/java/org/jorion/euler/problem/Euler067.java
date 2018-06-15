package org.jorion.euler.problem;

import java.util.List;

import org.jorion.euler.util.Utils;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
 * <pre>
 *    3
 *   7 4
 *  2 4 6
 * 8 5 9 3
 * </pre>
 * That is, 3 + 7 + 4 + 9 = 23.
 * <p>
 * Find the maximum total from top to bottom in triangle.txt, a 15K text file containing a triangle with one-hundred rows.
 * <p>
 * NOTE: This is a much more difficult version of Problem 18. It is not possible to try every route to solve this problem,
 * as there are 2^99 altogether! If you could check one trillion (10^12) routes every second,
 * it would take over twenty billion years to check them all. There is an efficient algorithm to solve it. ;o)
 */
public class Euler067 {

	// --- Methods ---
	public static void main(String[] args) throws Exception {

		long res; // 7273
		long delta;

		List<String> lines = Utils.readLines("p067_triangle.txt");
		int nLine = lines.size();

		final int size = nLine * (nLine + 1) / 2;
		int[] data = new int[size];
		int index = 0;
		for (String line : lines) {
			String[] numbers = line.split(" ");
			for (String number : numbers) {
				data[index++] = Integer.parseInt(number);
			}
		}

		Utils.start();
		res = calc1(data);
		delta = Utils.stop();
		Utils.print("Best ", res, delta);
	}

	/**
	 * Best solution: keep only the best solution for every element.
	 * <p>
	 * Complexity: O(nLine)
	 */
	private static int calc1(int[] data) {

		int nLine = computeLines(data.length);
		int[] val1 = new int[] { getElem(data, 0, 0) };

		for (int i = 1; i < nLine; i++) {
			int[] val2 = new int[i + 1];
			for (int j = 0; j < i + 1; j++) {
				int left = (j - 1 >= 0) ? val1[j - 1] : 0;
				int right = (j < i) ? val1[j] : 0;
				val2[j] = getElem(data, i, j) + Math.max(left, right);
			}
			val1 = val2;
		}

		int best = 0;
		for (int val : val1) {
			if (val > best) {
				best = val;
			}
		}
		return best;
	}

	/**
	 * i = [0, line[; j = [0, i]
	 *
	 * @return element corresponding to indexes i, j
	 */
	private static int getElem(int[] data, int i, int j) {

		return data[i * (i + 1) / 2 + j];
	}

	private static int computeLines(int len) {

		return (int) (Math.sqrt(8 * len + 1) - 1) / 2;
	}
}
