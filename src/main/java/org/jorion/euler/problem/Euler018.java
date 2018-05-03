package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from
 * top to bottom is 23.
 * 
 * <pre>
 *    3
 *   7 4
 *  2 4 6
 * 8 5 9 3
 * </pre>
 * 
 * That is, 3 + 7 + 4 + 9 = 23.
 *
 * Find the maximum total from top to bottom of the triangle below:
 * <p>
 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem
 * 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and
 * requires a clever method! ;o)
 */
public class Euler018
{

    // --- Constants ---
    // @formatter:off
	@SuppressWarnings("unused")
	private static final int[] T0 = {
			   3,
			  7, 4,
			 2, 4, 6,
			8, 5, 9, 3 };

	private static final int[] T1 = {
			75,
			95, 64,
			17, 47, 82,
			18, 35, 87, 10,
			20,  4, 82, 47, 65,
			19,  1, 23, 75,  3, 34,
			88,  2, 77, 73,  7, 63, 67,
			99, 65,  4, 28,  6, 16, 70, 92,
			41, 41, 26, 56, 83, 40, 80, 70, 33,
			41, 48, 72, 33, 47, 32, 37, 16, 94, 29,
			53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14,
			70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57,
			91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48,
			63, 66,  4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31,
			 4, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23
	};
	// @formatter:on

    // --- Methods ---
    public static void main(String[] args)
    {
        long res; // 1074
        long delta;

        Utils.start();
        res = calc1(T1);
        delta = Utils.stop();
        Utils.print("Approx. (wrong)", res, delta);

        Utils.start();
        res = calc2(T1);
        delta = Utils.stop();
        Utils.print("Brute Force    ", res, delta);

        Utils.start();
        res = calc3(T1);
        delta = Utils.stop();
        Utils.print("Smartest (best)", res, delta);
    }

    /** By closest neighbour (wrong solution). */
    private static int calc1(int[] data)
    {
        int nLine = computeLines(data.length);
        int sum = getElem(data, 0, 0);
        int j = 0;
        for (int i = 1; i < nLine; i++) {
            int val1 = getElem(data, i, j);
            int val2 = getElem(data, i, j + 1);
            if (val2 > val1) {
                j++;
                sum += val2;
            }
            else {
                sum += val1;
            }
        }
        return sum;
    }

    /**
     * Check all possible combinations. Best path: 10111110010011 (= 12179) (75 =>
     * 64-82-87-82-75-73-28-83-32-91-78-58-73-93)
     * <p>
     * Complexity: O(nLine²)
     */
    private static int calc2(int[] data)
    {
        int best = 0;
        // int bestPath = 0;
        int nLine = computeLines(data.length);
        int nPath = (int) Math.pow(2, nLine - 1);

        for (int path = 0; path < nPath; path++) {
            int sum = getElem(data, 0, 0);
            int j = 0;
            int mask = 1;
            for (int i = 1; i < nLine; i++) {
                if ((path & mask) > 0) {
                    j++;
                }
                mask <<= 1;
                int val = getElem(data, i, j);
                sum += val;
            }
            if (sum > best) {
                best = sum;
                // bestPath = path;
            }
        }
        return best;
    }

    /**
     * Best solution: keep only the best solution for every element.
     * <p>
     * Complexity: O(nLine)
     */
    private static int calc3(int[] data)
    {
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
    private static int getElem(int[] data, int i, int j)
    {
        return data[i * (i + 1) / 2 + j];
    }

    private static int computeLines(int len)
    {
        return (int) (Math.sqrt(8 * len + 1) - 1) / 2;
    }
}
