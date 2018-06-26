package org.jorion.euler.util;

public class MathUtils
{
    // --- Methods ---
    protected static void wakeUp()
    {}

    /**
     * Formula: C<sup>p</sup><sub>n</sub> = n! / (p! * (n - p)!)
     *
     * @param p the small number (0 &le; p &le; n)
     * @param n the large number
     * @return the computed value
     */
    public static long cpn(int p, int n)
    {
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
     * Find the Greatest Common Divisor (gcd) between two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the gcd between a and b
     * @see https://en.wikipedia.org/wiki/Euclidean_algorithm
     */
    public static int gcd(int a, int b)
    {
        int remainder;
        while (b != 0) {
            remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
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
    public static int[] simplify(int num, int den)
    {
        int gcd = gcd(num, den);
        num = num / gcd;
        den = den / gcd;
        int[] res = { num, den };
        return res;
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
    public static boolean isPandigital(int n)
    {
        boolean pandigital = true;
        int[] digits = new int[10];
        digits[0] = 1;
        
        int count = 0;
        while (n > 0) {
            int mod = n % 10;
            if (digits[mod] > 0) {
                pandigital = false;
                break;
            }
            else {
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

    public static boolean isUnique(int n, boolean zeroAllowed)
    {
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
            }
            else {
                digits[mod] = 1;
            }
            n /= 10;
        }
        return unique;
    }
}
