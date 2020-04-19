package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper
 * fraction.
 * <p>
 * If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
 * <p>
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * <p>
 * It can be seen that there are 21 elements in this set.
 * <p>
 * How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?
 */
public class Euler072
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1_000_000;
        long res; // 303_963_552_391
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    /**
     * For a given number d, the list of all possible fractions are n/d where n < d. Fractions that cannot be
     * simplified: only if n and d are relatively primes. This corresponds to phi(d) (see Euler070).
     */
    private static long calc1(int d)
    {
        final boolean[] primes = PrimeUtils.isPrimeSoE(d);

        long sum = 0;
        for (int i = 2; i <= d; i++) {
            sum += PrimeUtils.phi(i, primes);
        }
        return sum;
    }

}