package org.jorion.euler.problem;

import java.math.BigDecimal;
import java.util.Arrays;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * The Fibonacci sequence is defined by the recurrence relation:
 * <p>
 * Fn = Fn−1 + Fn−2, where F<sub>1</sub> = 1 and F<sub>2</sub> = 1.
 * <p>
 * It turns out that F<sub>541</sub>, which contains 113 digits, is the first Fibonacci number for which the last nine
 * digits are 1-9 pandigital (contain all the digits 1 to 9, but not necessarily in order): 839725641.
 * <p>
 * And F<sub>2749</sub>, which contains 575 digits, is the first Fibonacci number for which the first nine digits are
 * 1-9 pandigital.
 * <p>
 * Given that F<sub>k</sub> is the first Fibonacci number for which the first nine digits AND the last nine digits are
 * 1-9 pandigital, find k.
 */
public class Euler104
{
    // --- Constants ---
    private static final char[] PAN = "123456789".toCharArray();

    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 0;
        long res; // 329468
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    /**
     * This algorithm takes < 100ms.
     * <p>
     * For the last part, we keep only the last 10 digits.
     */
    private static long calc1(int max)
    {
        final long min = 123_456_789L;
        final long ten06 = 1_000_000L;
        final long ten09 = 1_000_000_000L;
        final long ten15 = 1_000_000_000_000_000L;

        long f1 = 1;
        long f2 = 1;
        long g1 = 1;
        long g2 = 1;

        int count = 2;
        while (++count > 0) {
            long f = f1 + f2;
            f1 = f2;
            f2 = f;
            if (f2 > ten09) {
                f1 %= ten09;
                f2 %= ten09;
            }

            long g = g1 + g2;
            g1 = g2;
            g2 = g;
            if (g2 > ten15) {
                g1 /= 10;
                g2 /= 10;
            }

            // check last digits
            if (f2 >= min && MathUtils.isPandigital((int) f2)) {
                // System.out.println(String.format("%s => f: %s", count, f2));
            }
            else {
                continue;
            }

            // check first digits
            // g2 <= 10**15; temp <= 10**(15-6) = 10**9
            long tmp = g2 / ten06;
            if (tmp >= min && MathUtils.isPandigital((int) tmp)) {
                // System.out.println(String.format("%s => g: %s", count, g));
            }
            else {
                continue;
            }

            break;
        }

        return count;
    }

    /**
     * This algorithm takes about 75'.
     */
    @SuppressWarnings("unused")
    private static long calc2(int max)
    {
        BigDecimal f1 = new BigDecimal(1);
        BigDecimal f2 = new BigDecimal(1);

        int done = 0;
        int count = 3;
        while (done < 2) {
            // System.out.println(count);
            done = 0;
            BigDecimal f = f2.add(f1);
            f1 = f2;
            f2 = f;
            if (isPandigital(f.toPlainString(), false)) {
                System.out.println("last  => " + count);
                done++;
            }
            if (isPandigital(f.toPlainString(), true)) {
                System.out.println("first => " + count);
                done++;
            }
            count++;
        }
        return --count;
    }

    static boolean isPandigital(String value, boolean first)
    {
        if (value.length() <= 9) {
            return false;
        }
        String str = (first) ? value.substring(0, 9) : value.substring(value.length() - 9);
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return Arrays.equals(arr, PAN);
    }

}