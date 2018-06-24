package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three
 * solutions for p = 120.
 * <p>
 * {20,48,52}, {24,45,51}, {30,40,50}
 * <p>
 * For which value of p ≤ 1000, is the number of solutions maximised?
 */
public class Euler039
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1_000;
        long res; // 840
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static long calc1(int max)
    {
        int[] values = new int[max + 1];
        for (int i = 1; i < max / 2; i++) {
            for (int j = i + 1; j < max / 2; j++) {
                int sqr = i * i + j * j;
                int sqrt = (int) Math.sqrt(sqr);
                if (sqrt * sqrt == sqr) {
                    int p = i + j + sqrt;
                    if (p < max) {
                        values[p] = values[p] + 1;
                    }
                }
            }
        }

        int count = 0;
        int val = 0;
        for (int i = 0; i < max; i++) {
            if (values[i] > count) {
                count = values[i];
                val = i;
            }
        }
        return val;
    }

}