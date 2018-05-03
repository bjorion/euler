package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
 * 
 * <pre>
 * 21 22 23 24 25
 * 20  7  8  9 10
 * 19  6  1  2 11
 * 18  5  4  3 12
 * 17 16 15 14 13
 * </pre>
 * 
 * It can be verified that the sum of the numbers on the diagonals is 101.
 * <p>
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 */
public class Euler028
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1001;
        int res;
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Incremental  ", res, delta);

        Utils.start();
        res = calc2(max);
        delta = Utils.stop();
        Utils.print("Direct (Best)", res, delta);
    }

    /**
     * Let be the center (1) coordinates(0, 0)
     * <ul>
     * <li>formula for the top/right: 4n² + 4n + 1
     * <li>formula for the bottom/right: 4n² - 2n + 1
     * <li>formula for the top/left: 4n² + 2n + 1
     * <li>formula for the bottom/left: 4n² + 1
     * <ul>
     * Sum: 16n² + 4n + 4 = 4(4n² + n + 1)
     */
    private static int calc1(int max)
    {
        int n = (max - 1) / 2;
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum += 4 * (4 * i * i + i + 1);
        }
        return sum;
    }

    /**
     * Similar to calc1, but with a direct function.
     */
    private static int calc2(int max)
    {
        int n = (max - 1) / 2;
        int sum = 8 * n * (n + 1) * (2 * n + 1) / 3 + 2 * n * (n + 1) + 4 * n + 1;
        return sum;
    }
}
