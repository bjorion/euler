package org.jorion.euler.problem;

import java.util.Arrays;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * The primes <b>3</b>, <b>7</b>, <b>109</b>, and <b>673</b>, are quite remarkable. By taking any two primes and
 * concatenating them in any order the result will always be prime. For example, taking <b>7</b> and <b>109</b>, both
 * <b>7109</b> and <b>1097</b> are prime. The sum of these four primes, <b>792</b>, represents the lowest sum for a set
 * of four primes with this property.
 * <p>
 * Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
 */
public class Euler060
{
    // --- Methods ---
    public static void main(String[] args)
    {
        // [13, 5197, 5701, 6733, 8389]
        // sum = 26033
        final int max = 5;
        long res;
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Brute force", res, delta);
    }

    /**
     * Note: it would be possible to simplify this method using recursion.
     */
    private static long calc1(int max)
    {
        final int limit = 10000;
        boolean[] primes = PrimeUtils.isPrimeSoE(limit * limit);
        int[] n = null;
        int res = Integer.MAX_VALUE; // 2.147.483.647

        for (int a = 3; a < limit; a += 2) {
            int maxA = max;
            if (!primes[a]) {
                continue;
            }
            if (a * maxA >= res) {
                break;
            }
            for (int b = a + 2; b < limit; b += 2) {
                int maxB = maxA - 1;
                int sumA = a;
                if (!primes[b]) {
                    continue;
                }
                if (sumA + b * maxB >= res) {
                    break;
                }
                if (!checkPrimes(primes, a, b)) {
                    continue;
                }
                for (int c = b + 2; c < limit; c += 2) {
                    int maxC = maxB - 1;
                    int sumB = sumA + b;
                    if (!primes[c]) {
                        continue;
                    }
                    if (sumB + c * maxC >= res) {
                        break;
                    }
                    if (!checkPrimes(primes, a, c) || !checkPrimes(primes, b, c)) {
                        continue;
                    }
                    for (int d = c + 2; d < limit; d += 2) {
                        int maxD = maxC - 1;
                        int sumC = sumB + c;
                        if (!primes[d]) {
                            continue;
                        }
                        if (sumC + d * maxD >= res) {
                            break;
                        }
                        if (!checkPrimes(primes, a, d) || !checkPrimes(primes, b, d) || !checkPrimes(primes, c, d)) {
                            continue;
                        }
                        for (int e = d + 2; e < limit; e += 2) {
                            int maxE = maxD - 1;
                            int sumD = sumC + d;
                            if (!primes[e]) {
                                continue;
                            }
                            if (sumD + e * maxE >= res) {
                                break;
                            }
                            if (!checkPrimes(primes, a, e) || !checkPrimes(primes, b, e) || !checkPrimes(primes, c, e) || !checkPrimes(primes, d, e)) {
                                continue;
                            }
                            // end
                            int sumE = sumD + e;
                            res = sumE;
                            System.out.println("found: " + res);
                            n = new int[] { a, b, c, d, e };
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(n));
        return res;
    }

    /**
     * Check that "ab" and "ba" are primes.
     */
    private static boolean checkPrimes(boolean[] primes, int a, int b)
    {
        boolean ok = false;
        if (primes[(int) MathUtils.concatenate(a, b)]) {
            if (primes[(int) MathUtils.concatenate(b, a)]) {
                ok = true;
            }
        }
        return ok;
    }

}
