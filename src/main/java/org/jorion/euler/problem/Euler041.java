package org.jorion.euler.problem;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example,
 * 2143 is a 4-digit pandigital and is also prime.
 * <p>
 * What is the largest n-digit pandigital prime that exists?
 */
public class Euler041
{
    // --- Methods ---
    /**
     * What is missing is a way to permute digits alphabetically (or inverse)
     */
    public static void main(String[] args)
    {
        long res; // 7652413 
        long delta;

        Utils.start();
        res = calc1(); // 37ms
        delta = Utils.stop();
        Utils.print("Top to Bottom ", res, delta);

        Utils.start();
        res = calc2(); // 10ms
        delta = Utils.stop();
        Utils.print("Permutations  ", res, delta);
    }

    /**
     * Note: Nine numbers cannot be done (1+2+3+4+5+6+7+8+9=45 => always divisible by 3) <br>
     * Note: Eight numbers cannot be done (1+2+3+4+5+6+7+8=36 => always divisible by 3)
     */
    private static long calc1()
    {
        final int max = 10_000_000;

        int res = 0;
        for (int i = max - 1; i > 0; i = i - 2) {
            if (MathUtils.isPandigital(i)) {
                if (PrimeUtils.isPrime6(i)) {
                    res = i;
                    break;
                }
            }
        }
        return res;
    }

    private static long calc2()
    {
        String str = "1234567";
        int best = permute(str, 0, str.length() - 1, 0);
        return best;
    }

    /**
     * Permutation here is not "alphabetical".
     */
    private static int permute(String str, int l, int r, int best)
    {
        if (l == r) {
            int val = Integer.parseInt(str);
            if (val > best && PrimeUtils.isPrime6(val)) {
                best = val;
            }
        }
        else {
            for (int i = l; i <= r; i++) {
                str = WordUtils.swap(str, l, i);
                best = permute(str, l + 1, r, best);
                str = WordUtils.swap(str, l, i);
            }
        }
        return best;
    }

}