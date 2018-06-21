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
     * Given an array of booleans, returns the index of the next "true" in the array.
     *
     * @param arr an array of booleans
     * @param index the current index
     * @return the index of the next true (greater than the given index), or -1 if it does not exist
     */
    public static int nextTrue(boolean[] arr, int index)
    {
        int len = arr.length;
        while (index < len) {
            index++;
            if (arr[index]) {
                break;
            }
        }
        return (index < len) ? index : -1;
    }

}
