package org.jorion.euler.problem;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.jorion.euler.util.Utils;

/**
 */
public class Euler080
{
    private static final int LEN = 100;

    private static final int ASCII_0 = 48;

    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 100;
        long res; // 40886
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static long calc1(int max)
    {
        final MathContext mc = new MathContext(102, RoundingMode.DOWN);
        long sum = 0;

        for (int i = 2; i <= max; i++) {
            int sqrt = (int) Math.sqrt(i);
            if (sqrt * sqrt < i) {
                var bd = BigDecimal.valueOf(i);
                var bdSqrt = bd.sqrt(mc);
                int val = sumDigits(bdSqrt);
                sum += val;
                // System.out.println("computing for :" + i + ", sum: " + val);
            }
        }
        return sum;
    }

    static int sumDigits(BigDecimal bd)
    {
        String value = bd.toPlainString();
        int max = (value.length() < LEN + 1) ? value.length() : LEN + 1;
        value = value.substring(0, max);
        int pos = value.indexOf('.');
        int sum = Integer.parseInt(value.substring(0, pos));

        String decimalPart = value.substring(pos + 1);
        for (int i = 0; i < decimalPart.length(); i++) {
            int d = (decimalPart.charAt(i) - ASCII_0);
            sum += d;
        }
        return sum;
    }

}