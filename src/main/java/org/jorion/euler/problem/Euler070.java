package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of positive
 * numbers less than or equal to n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less
 * than nine and relatively prime to nine, φ(9)=6.
 * <p>
 * The number 1 is considered to be relatively prime to every positive number, so φ(1)=1.
 * <p>
 * Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
 * <p>
 * Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum.
 */
public class Euler070
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 10_000_000;
        long res; // 8319823 = 2339 * 3557; phi(n) = 8313928
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static int calc1(int max)
    {
        final boolean[] primes = PrimeUtils.isPrimeSoE(max);
        double min = 2;
        int res = -1;
        for (int n = 3; n < max; n = n + 2) {
            if (n % 3 == 0) {
                continue;
            }
            // ignore prime (phi(n) = n - 1; no permutation possible)
            if (primes[n]) {
                continue;
            }
            // n > phi(n)
            int phi = PrimeUtils.phi(n, primes);
            // check n and phi(n) have the same number of digits
            if (n >= 10 * phi) {
                continue;
            }
            String a = Integer.toString(n);
            String b = Integer.toString(phi);
            if (WordUtils.isPermutation(a, b)) {
                // System.out.println(a + ": " + b);
                if (n < phi * min) {
                    min = (double) n / phi;
                    res = n;
                    // System.out.println("n: " + n + ", min: " + min);
                }
            }
        }
        return res;
    }

}