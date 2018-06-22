package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * An irrational decimal fraction is created by concatenating the positive integers:
 * 
 * <pre>
 * 0.12345678910(1)112131415161718192021...
 * </pre>
 * 
 * It can be seen that the 12th digit of the fractional part is 1.
 * <p>
 * If dn represents the nth digit of the fractional part, find the value of the following expression.
 * <p>
 * d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
 */
public class Euler040
{
    // --- Constants ---
    private static final int[] D = { 1, 10, 100, 1_000, 10_000, 100_000, 1_000_000 };

    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1_000_000;
        long res; // 210
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Brute Force ", res, delta);

        Utils.start();
        res = calc2(max);
        delta = Utils.stop();
        Utils.print("Less Memory ", res, delta);
    }

    /**
     * Store in memory all digits (bad).
     */
    private static long calc1(int max)
    {
        int res = 1;
        char[] digits = new char[max + 10];
        int index = 1;
        for (int i = 1; index <= max; i++) {
            String str = Integer.toString(i);
            for (int j = 0; j < str.length(); j++) {
                digits[index++] = str.charAt(j);
            }
        }
        for (int i = 0; i < D.length && D[i] < index; i++) {
            res *= (digits[D[i]] - '0');
        }
        // System.out.println(digits);
        return res;
    }

    /**
     * Use indexes to save memory (good).
     */
    private static long calc2(int max)
    {
        int res = 1;
        int index = 1, i = 1;
        int dIndex = 0;

        while (index <= max) {
            String str = Integer.toString(i);
            int len = str.length();
            if (index + len < D[dIndex]) {
                index += len;
            }
            else {
                for (int j = 0; j < len; j++) {
                    if (dIndex < D.length && index == D[dIndex]) {
                        dIndex++;
                        res *= (str.charAt(j) - '0');
                    }
                    index++;
                }
            }
            i++;
        }

        return res;
    }

}