package org.jorion.euler.util;

import java.math.BigInteger;

public class MathUtils {

    public static final BigInteger TWO = new BigInteger("2");

    /**
     * Powers of 10 from 0 to 9: M10[n] = 10 ^ n.
     */
    static final int[] M10 = new int[10];

    protected static void wakeUp() {
    }

    /**
     * Compute the sum of 1 to num.
     *
     * @param num the number to use
     * @return the sum of 1 to num
     */
    public static long sum(long num) {
        return num * (num + 1) / 2;
    }

    /**
     * Formula: C<sup>p</sup><sub>n</sub> = n! / (p! * (n - p)!)
     *
     * @param p the small number (0 &le; p &le; n)
     * @param n the large number
     * @return the computed value
     */
    public static long cpn(int p, int n) {
        if (p == 0 || p == n) {
            return 1;
        }
        if (p > n / 2) {
            p = n - p;
        }
        long res = n - p + 1;
        for (int i = 2; i <= p; i++) {
            res = res * (n - p + i) / i;
        }
        return res;
    }

    /**
     * Find the Greatest Common Divisor (GCD = PGCD) between two integers.
     * cf. <a href="https://en.wikipedia.org/wiki/Euclidean_algorithm">...</a>
     *
     * @param a the first integer
     * @param b the second integer
     * @return the gcd between a and b
     */
    public static int gcd(int a, int b) {
        int remainder;
        while (b != 0) {
            remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    public static long gcd(long a, long b) {
        long remainder;
        while (b != 0) {
            remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger rem;
        while (!b.equals(BigInteger.ZERO)) {
            rem = a.mod(b);
            a = b;
            b = rem;
        }
        return a;
    }

    /**
     * Find the Lowest Common Multiple (LCM = PPCM) between two integers.
     * cf. <a href="https://en.wikipedia.org/wiki/Least_common_multiple">...</a>
     *
     * @param a the first integer
     * @param b the second integer
     * @return the lcm between a and b
     */
    public static long lcm(long a, long b) {
        long m = a * b;
        if (m < 0) {
            throw new IllegalStateException("a*b is larger than Long.MAX_VALUE");
        }
        long gcd = gcd(a, b);
        return m / gcd;
    }

    /**
     * Simplify the given fraction (num/den).
     * <p>
     * Example: "2 / 10" will return "1 / 5"
     *
     * @param num the numerator
     * @param den the denumerator
     * @return the fraction simplified (first elem is num, second elem is denum)
     */
    public static int[] simplify(int num, int den) {
        int gcd = gcd(num, den);
        num = num / gcd;
        den = den / gcd;
        return new int[]{num, den};
    }

    public static long[] simplify(long num, long den) {
        long gcd = gcd(num, den);
        num = num / gcd;
        den = den / gcd;
        return new long[]{num, den};
    }

    public static BigInteger[] simplify(BigInteger num, BigInteger den) {
        BigInteger gcd = gcd(num, den);
        num = num.divide(gcd);
        den = den.divide(gcd);
        return new BigInteger[]{num, den};
    }

    /**
     * Add the two given fractions. Each fraction is an array of two integers (first elem is num, second elem is denum).
     *
     * @param f1 the first fraction
     * @param f2 the second fraction
     * @return the sum of the fraction, simplified
     */
    public static long[] addFractions(long[] f1, long[] f2) {
        long num = f1[0] * f2[1] + f1[1] * f2[0];
        long den = f1[1] * f2[1];
        return simplify(num, den);
    }

    public static BigInteger[] addFractions(BigInteger[] f1, BigInteger[] f2) {
        BigInteger num = f1[0].multiply(f2[1]).add(f1[1].multiply(f2[0]));
        BigInteger den = f1[1].multiply(f2[1]);
        return simplify(num, den);
    }

    /**
     * A n-digit number is pandigital if it makes use of all the digits 1 to n exactly once.
     * <p>
     * Example: 2143 is a 4-digit pandigital.
     * <p>
     * This definition excludes "0".
     *
     * @param n the number to test
     * @return true if n is pandigital.
     */
    public static boolean isPandigital(int n) {
        boolean pandigital = true;
        int[] digits = new int[10];
        digits[0] = 1;

        int count = 0;
        while (n > 0) {
            int mod = n % 10;
            if (digits[mod] > 0) {
                pandigital = false;
                break;
            } else {
                digits[mod] = 1;
                count++;
            }
            n /= 10;
        }
        if (pandigital) {
            for (int i = 1; i <= count; i++) {
                if (digits[i] == 0) {
                    pandigital = false;
                    break;
                }
            }
        }
        return pandigital;
    }

    /**
     * Return true if the given number is made of only unique digits.
     *
     * @param n           the integer to analyze
     * @param zeroAllowed true if zero is accepted as a digit
     * @return true if each digit of n appears only once
     */
    public static boolean isUnique(int n, boolean zeroAllowed) {
        boolean unique = true;
        int[] digits = new int[10];
        if (!zeroAllowed) {
            digits[0] = 1;
        }
        while (n > 0) {
            int mod = n % 10;
            if (digits[mod] > 0) {
                unique = false;
                break;
            } else {
                digits[mod] = 1;
            }
            n /= 10;
        }
        return unique;
    }

    static {
        M10[0] = 1;
        for (int i = 1; i < M10.length; i++) {
            M10[i] = M10[i - 1] * 10;
        }
    }

    /**
     * Concatenate two positive integers (gte 0) without using strings.
     */
    public static long concatenate(int a, int b) {
        int nDigit = (b > 0) ? ((int) Math.log10(b) + 1) : 1;
        return (long) M10[nDigit] * a + b;
    }

    /**
     * This method is much less performant than {@link #concatenate(int, int)}.
     */
    @Deprecated
    public static long concatenateStr(int a, int b) {
        String res = Integer.toString(a) + Integer.toString(b);
        return Long.parseLong(res);
    }
}
