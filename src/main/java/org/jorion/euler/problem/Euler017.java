package org.jorion.euler.problem;

import java.util.HashMap;
import java.util.Map;

import org.jorion.euler.util.Utils;

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19
 * letters used in total.
 * <p>
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 * <p>
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one
 * hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British
 * usage.
 */
public class Euler017
{
    private static final Map<Integer, Integer> N = new HashMap<>();

    private static final String AND = "and";

    static {
        N.put(1, "one".length());
        N.put(2, "two".length());
        N.put(3, "three".length());
        N.put(4, "four".length());
        N.put(5, "five".length());
        N.put(6, "six".length());
        N.put(7, "seven".length());
        N.put(8, "eight".length());
        N.put(9, "nine".length());
        N.put(10, "ten".length());
        N.put(11, "eleven".length());
        N.put(12, "twelve".length());
        N.put(13, "thirteen".length());
        N.put(14, "fourteen".length());
        N.put(15, "fifteen".length());
        N.put(16, "sixteen".length());
        N.put(17, "seventeen".length());
        N.put(18, "eighteen".length());
        N.put(19, "nineteen".length());
        N.put(20, "twenty".length());
        N.put(30, "thirty".length());
        N.put(40, "forty".length());
        N.put(50, "fifty".length());
        N.put(60, "sixty".length());
        N.put(70, "seventy".length());
        N.put(80, "eighty".length());
        N.put(90, "ninety".length());
        N.put(100, "hundred".length());
        N.put(1000, "thousand".length());
    }

    public static void main(String[] args)
    {
        final int max = 1000;
        long res = 0;
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Brute force", res, delta);
    }

    /**
     * Compute number of digits from 1 to 10000 (and more)
     */
    private static long calc1(final int max)
    {
        long res = 0;

        for (int i = 1; i <= max; i++) {
            int n = i;
            int du = n % 100;
            int u = n % 10;
            n /= 10;
            int d = n % 10;
            n /= 10;
            int c = n % 10;
            n /= 10;
            int m = n % 10;

            if (m > 0) {
                res += N.get(m) + N.get(1000);
                // System.out.print(N.get(m) + " " + N.get(1000));
            }

            // 100-999
            if (c > 0) {
                res += N.get(c) + N.get(100);
                // System.out.print(N.get(c) + " " + N.get(100));
                if (du > 0) {
                    res += AND.length();
                    // System.out.print(" " + AND + " ");
                }
            }

            // 0
            if (du == 0) {

            }
            // 1-20
            else if (du >= 1 && du <= 19) {
                res += N.get(du);
                // System.out.print(N.get(du));
            }
            // 21-99
            else {
                res += N.get(d * 10);
                // System.out.print(N.get(d * 10));
                if (u != 0) {
                    res += N.get(u);
                    // System.out.print(" " + N.get(u));
                }
            }

            // System.out.println();
        }

        return res;
    }
}
