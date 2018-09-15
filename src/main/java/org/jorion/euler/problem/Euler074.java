package org.jorion.euler.problem;

import java.util.HashSet;
import java.util.Set;

import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:
 * <p>
 * 1! + 4! + 5! = 1 + 24 + 120 = 145
 * <p>
 * Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169; it turns out
 * that there are only three such loops that exist:
 * 
 * <pre>
 * 169 → 363601 → 1454 → 169
 * 871 → 45361 → 871
 * 872 → 45362 → 872
 * </pre>
 * 
 * It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,
 * 
 * <pre>
 * 69 → 363600 → 1454 → 169 → 363601 (→ 1454)
 * 78 → 45360 → 871 → 45361 (→ 871)
 * 540 → 145 (→ 145)
 * </pre>
 * 
 * Starting with 69 produces a chain of five non-repeating terms, but the longest non-repeating chain with a starting
 * number below one million is sixty terms.
 * <p>
 * How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?
 */
public class Euler074
{
    // --- Constants ---
    private static final int[] FACT = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1_000_000;
        long res; // 402
        long delta; 

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Simple ", res, delta);
    }

    private static int calc1(int max)
    {
        final int limit = 60;
        int res = 0;
        for (int i = 1; i < max; i++) {
            String str = Integer.toString(i);
            int n = i;
            Set<String> set = new HashSet<>();
            do {
                set.add(str);
                n = f(str);
                str = Integer.toString(n);
            }
            while (!set.contains(str));
            // System.out.println(i + ": " + count + ": " + str);
            if (set.size() == limit) {
                res++;
            }
        }

        return res;
    }

    private static int f(String val)
    {
        int sum = 0;
        char[] digits = val.toCharArray();
        for (char digit : digits) {
            int d = (digit - WordUtils.ASCII_0);
            sum += FACT[d];
        }
        return sum;
    }

}