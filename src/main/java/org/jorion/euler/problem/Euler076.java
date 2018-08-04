package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * It is possible to write five as a sum in exactly six different ways:
 *
 * <pre>
 * 4 + 1
 * 3 + 2
 * 3 + 1 + 1
 * 2 + 2 + 1
 * 2 + 1 + 1 + 1
 * 1 + 1 + 1 + 1 + 1
 * </pre>
 *
 * How many different ways can one hundred be written as a sum of at least two positive integers?
 */
public class Euler076
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 100;
        long res; // 190569291
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    /**
     * Recursive algorithm.
     * 
     * <pre>
     * 5 = 5                 = # solutions for 5 - 5 = (0) limited to 0 => 1
     * 5 = 4 + 1             = # solutions for 5 - 4 = (1) limited to 1 => 1
     * 5 = 3 + 2             = # solutions for 5 - 3 = (2) limited to 2 => 2
     * 5 = 3 + 1 + 1
     * 5 = 2 + 2 + 1         = # solutions for 5 - 2 = (3) limited to 2 => 2
     * 5 = 2 + 1 + 1 + 1
     * 5 = 1 + 1 + 1 + 1 + 1 = # solutions for 5 - 1 = (4) limited to 1 => 1
     * </pre>
     * 
     * #solutions for 5 = 7 (that includes the extra solution: 5=5)
     */
    private static long calc1(int max)
    {
        int[][] arr = new int[max + 1][];

        arr[0] = new int[] { 1 };
        for (int i = 1; i <= max; i++) {
            // cur = cumulative number of solutions
            int[] cur = new int[i + 1];
            for (int j = 1; j <= i; j++) {
                int delta = i - j;
                int index = arr[delta].length - 1;
                if (index > j) {
                    index = j;
                }
                cur[j] = cur[j - 1] + arr[delta][index];
            }
            arr[i] = cur;
        }

        // for (int i = 0; i < arr.length; i++) {
        // System.out.println(i + ":" + Arrays.toString(arr[i]));
        // }

        // we substract 1 to remove the case where "max" can be written with only one number (max = max)
        return arr[max][max] - 1;
    }

}