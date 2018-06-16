package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * Euler discovered the remarkable quadratic formula:
 * 
 * <pre>
 * n ^ 2 + n + 41
 * </pre>
 * 
 * It turns out that the formula will produce 40 primes for the consecutive integer values 0≤n≤39 . However, when n=40,
 * 40^2+40+41=40(40+1)+41 is divisible by 41, and certainly when n=41,41^2+41+41 is clearly divisible by 41.
 * <p>
 * The incredible formula n2−79n+1601 was discovered, which produces 80 primes for the consecutive values 0≤n≤79. The
 * product of the coefficients, −79 and 1601, is −126479.
 * <p>
 * Considering quadratics of the form:
 * 
 * <pre>
 * n^2+an+b, where |a| < 1000 and |b| ≤ 1000
 * </pre>
 * 
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of
 * primes for consecutive values of n, starting with n=0.
 */
public class Euler027
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1000;
        long res; // a=-61; b=971; n=71; res=-59231
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static long calc1(int max)
    {
        int limit = 1000;
        // n*limit: by trial and error
        boolean[] primes = PrimeUtils.isPrimeSoE(13 * limit);

        int nPrime = 0;
        int res = 0;
        int a = 0;
        int b = max - 1;
        while (b > 0) {
            // b must be prime because fn(0) = b
            if (primes[b]) {
                for (a = -max + 1; a < max; a++) {
                    int count = 0;
                    for (int n = 0; n < b; n++) {
                        int val = fn(n, a, b);
                        if (val > 0 && primes[val]) {
                            count++;
                        }
                        else {
                            break;
                        }
                    }
                    if (count > nPrime) {
                        nPrime = count;
                        res = a * b;
                        // System.out.println("count: " + count + ", a: " + a + ", b: " + b);
                    }
                }
            }
            b = b - 2;
            if (b < nPrime) {
                // System.out.println("nPrime: " +nPrime + ", b:" + b);
                break;
            }
        }
        return res;
    }

    private static int fn(int n, int a, int b)
    {
        return n * n + a * n + b;
    }
}