package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * The largest integer ≤ 100 that is only divisible by both the primes 2 and 3 is 96, as 96 = 32 * 3 = (2 ^ 5) * (3 ^
 * 1). For two distinct primes p and q let <b>M(p,q,N)</b> be the largest positive integer ≤ N only divisible by both p
 * and q and M(p,q,N) = 0 if such a positive integer does not exist.
 * <p>
 * E.g. M(2,3,100) = 96.
 * <p>
 * M(3,5,100) = 75 and not 90 because 90 is divisible by 2 ,3 and 5. <br/>
 * Also M(2,73,100) = 0 because there does not exist a positive integer ≤ 100 that is divisible by both 2 and 73.
 * <p>
 * Let S(N) be the sum of all distinct M(p,q,N). S(100) = 2262.
 * <p>
 * Find S(10 000 000).
 */
public class Euler347
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 10_000_000;
        long res; // 11_109_800_204_052 (+/- 170 ms)
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    /**
     * Hypothesis: p < q
     */
    private static long calc1(int n)
    {
        boolean[] primes = PrimeUtils.isPrimeSoE(n);
        int limit = (int) Math.sqrt(n);
        long sum = 0;

        // p < sqrt(n) since (p < q) and (p * q <= n)
        for (int p = 2; p < limit; p++) {
            if (!primes[p]) {
                continue;
            }
            int qmax = n / p;
            for (int q = p + 1; q <= qmax; q++) {
                if (!primes[q]) {
                    continue;
                }
                final boolean doOnlyOneLoop = (q > limit);
                // here p, q are primes with p < q
                // System.out.println("p: " + p + ", q: " + q);
                boolean done = false;
                long val = 1;
                long max = 0;
                // increase power of p
                while (!done) {
                    val *= p;
                    long val2 = val;
                    done = true;
                    // increase power of q
                    while (true) {
                        val2 *= q;
                        if (val2 > n) {
                            break;
                        }
                        done = false;
                        if (val2 > max) {
                            max = val2;
                        }
                        if (doOnlyOneLoop) {
                            break;
                        }
                    }
                }
                // System.out.println("max: " + max + ", p-q: " + p + ", " + q);
                sum += max;
            }
        }
        return sum;
    }

}