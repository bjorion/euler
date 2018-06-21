package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some
 * order, but it also has a rather interesting sub-string divisibility property.
 * <p>
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
 * 
 * <pre>
 *     d2d3d4=406 is divisible by 2
 *     d3d4d5=063 is divisible by 3
 *     d4d5d6=635 is divisible by 5
 *     d5d6d7=357 is divisible by 7
 *     d6d7d8=572 is divisible by 11
 *     d7d8d9=728 is divisible by 13
 *     d8d9d10=289 is divisible by 17
 * </pre>
 * 
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */
public class Euler043
{
    // --- Constants ---
    private static final int[] DIV = { 1, 2, 3, 5, 7, 11, 13, 17 };
    
    private static final int LEN = 3;

    // --- Methods ---
    public static void main(String[] args)
    {
        long res; // 16695334890
        long delta;

        Utils.start();
        res = calc1();
        delta = Utils.stop();
        Utils.print("Simple ", res, delta);
    }

    private static long calc1()
    {
        String str = "0123456789";
        long sum = 0;
        sum += permute(str, 0, str.length() - 1, sum);
        return sum;
    }

    /**
     * Permutation here is not "alphabetical".
     */
    private static long permute(String str, int l, int r, long sum)
    {
        if (l == r) {
            long val = checkNumber(str);
            if (val > 0) {
                sum += val;
            }
        }
        else {
            for (int i = l; i <= r; i++) {
                str = WordUtils.swap(str, l, i);
                sum = permute(str, l + 1, r, sum);
                str = WordUtils.swap(str, l, i);
            }
        }
        return sum;
    }

    private static long checkNumber(String str)
    {
        boolean ok = true;
        long sum = 0;
        for (int i = 1; i < DIV.length; i++) {
            int val = Integer.parseInt(str.substring(i, i + LEN));
            if (val % DIV[i] != 0) {
                ok = false;
                break;
            }
        }
        if (ok) {
            // System.out.println("adding: " + str);
            sum = Long.parseLong(str);
        }
        return (ok) ? sum : -1;
    }
}