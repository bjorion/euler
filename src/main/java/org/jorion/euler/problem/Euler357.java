package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * Consider the divisors of 30: 1,2,3,5,6,10,15,30. It can be seen that for every divisor d of 30, d+30/d is prime.
 * <p>
 * Find the sum of all positive integers n not exceeding 100 000 000 such that for every divisor d of n, d+n/d is prime.
 */
public class Euler357
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 100_000_000;
        long res; // 1_739_023_853_137
        long delta;

        // Utils.start();
        // res = calc1(max);
        // delta = Utils.stop();
        // Utils.print("Slow ", res, delta);
        
        Utils.start();
        res = calc2(max);
        delta = Utils.stop();
        Utils.print("Fast ", res, delta);

    }

    /**
     * Use {@link PrimeUtils.isPrime6(long)}
     * <p>
     * Time: 178sec :-(
     */
    @SuppressWarnings("unused")
    private static long calc1(int max)
    {
        long sum = 1; // "1" is valid (1 + 1/1 = 2)

        // we skip odd values (1 + n/1 is even if n is odd)
        for (int n = 2; n <= max; n = n + 2) {
            boolean flag = true;
            // test special case: 1
            if (!PrimeUtils.isPrime6(n + 1)) {
                continue;
            }
            int sqrt = (int) Math.sqrt(n);
            for (int d = 2; d <= sqrt; d++) {
                if (n % d != 0) {
                    continue;
                }
                int e = n / d;
                if (!PrimeUtils.isPrime6(d + e)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sum += n;
                // System.out.println(n);
            }
        }
        return sum;
    }

    /**
     * Pre-compute all prime numbers. Time is around 2.9ms :-)
     */
    private static long calc2(int max)
    {
        long sum = 1; // "1" is valid (1 + 1/1 = 2)
        boolean[] primes = PrimeUtils.isPrimeSoE(max + 1);

        // we skip odd values (1 + n/1 is even if n is odd)
        for (int n = 2; n <= max; n = n + 2) {
            // test special case: 1
            if (!primes[n + 1]) {
                continue;
            }
            int sqrt = (int) Math.sqrt(n);
            // skip squares
            if (sqrt * sqrt == n) {
                continue;
            }
            boolean flag = true;
            for (int d = 2; d <= sqrt; d++) {
                if (n % d != 0) {
                    continue;
                }
                int e = n / d;
                if (!primes[d + e]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sum += n;
                // System.out.println(n);
            }
        }
        return sum;
    }
}