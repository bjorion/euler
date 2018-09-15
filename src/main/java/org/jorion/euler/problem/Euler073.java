package org.jorion.euler.problem;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * Consider the fraction, n/d, where n and d are positive integers. If n &lt; d and GCD(n,d)=1, it is called a reduced
 * proper fraction.
 * <p>
 * If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
 * <p>
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * <p>
 * It can be seen that there are 3 fractions between 1/3 and 1/2.
 * <p>
 * How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d ≤ 12_000?
 */
public class Euler073
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 12_000;
        long res; // 7295372
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static int calc1(int max)
    {
        int count = 0;

        for (int d = 2; d <= max; d++) {
            // n > d/3 and n < d/2
            for (int n = d / 3 + 1; n <= d / 2; n++) {
                if (n % 2 == 0 && d % 2 == 0) {
                    continue;
                }
                if (2 * n >= d) {
                    continue;
                }
                if (3 * n <= d) {
                    continue;
                }
                if (MathUtils.gcd(n, d) == 1) {
                    // System.out.println("n: " + n + ", d: " + d + ", n/d: " + (float)n / d);
                    count++;
                }
            }
        }
        return count;
    }

}