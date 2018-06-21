package org.jorion.euler.problem;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may
 * incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 * <p>
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 * <p>
 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two
 * digits in the numerator and denominator.
 * <p>
 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 */
public class Euler033
{
    // --- Methods ---
    public static void main(String[] args)
    {
        long res;
        long delta;

        Utils.start();
        res = calc1();
        delta = Utils.stop();
        Utils.print("Simple ", res, delta);
    }

    private static long calc1()
    {
        int resn = 1, resd = 1;
        for (int d = 12; d <= 99; d++) {
            String den = Integer.toString(d);
            for (int n = 11; n < d; n++) {
                String num = Integer.toString(n);
                char c0 = num.charAt(1);
                if (c0 != '0' && den.indexOf(c0) != -1) {
                    int n2 = Integer.parseInt(remove(num, c0));
                    int d2 = Integer.parseInt(remove(den, c0));
                    if (n * d2 == n2 * d) {
                        // System.out.println("num: " + num + ", den: " + den);
                        resn *= n;
                        resd *= d;
                    }
                }
            }
        }
        int[] res = MathUtils.simplify(resn, resd);
        return res[1];
    }

    private static String remove(String from, char c)
    {
        String res = from;
        int pos = from.indexOf(c);
        if (pos >= 0) {
            res = from.substring(0, pos) + from.substring(pos + 1);
        }
        return res;
    }
}