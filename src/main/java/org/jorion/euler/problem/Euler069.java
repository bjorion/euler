package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of numbers less
 * than n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively
 * prime to nine, φ(9)=6.
 * 
 * <pre>
 * n   Relatively Prime    φ(n)    n/φ(n)
 * 2   1                   1       2
 * 3   1,2                 2       1.5
 * 4   1,3                 2       2
 * 5   1,2,3,4             4       1.25
 * 6   1,5                 2       3
 * 7   1,2,3,4,5,6         6       1.1666...
 * 8   1,3,5,7             4       2
 * 9   1,2,4,5,7,8         6       1.5
 * 10  1,3,7,9             4       2.5
 * </pre>
 * 
 * It can be seen that n=6 produces a maximum n/φ(n) for n ≤ 10.
 * <p>
 * Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum.
 */
public class Euler069
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1_000_000;
        long res; // 510_510
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    /**
     * To find max n/φ(n), we want n large and φ(n) small. A number that's a multiple of 2,3,5,7... is sure to fit those
     * conditions.
     * <p>
     * Hypothesis: we find the largest number (<= max) that's the product of the primes.
     */
    private static long calc1(int max)
    {
        boolean[] primes = PrimeUtils.isPrimeSoE(50);
        int n = 1, m = 1;
        for (int i = 0; i < primes.length; i++) {
            if (primes[i]) {
                m = n * i;
                if (m > max) {
                    break;
                }
                n = m;
            }
        }
        return n;
    }

    /**
     * One way to compute phi(n) but I am sure it's possible to do much better.
     */
    @SuppressWarnings("unused")
    private static List<Integer> phi(int n)
    {
        List<Integer> notPrimes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            boolean test = true;
            for (int j = 0; j < notPrimes.size(); j++) {
                if (i % notPrimes.get(j) == 0) {
                    notPrimes.add(i);
                    test = false;
                    break;
                }
            }
            if (test) {
                if (n % i == 0) {
                    notPrimes.add(i);
                }
            }
        }
        List<Integer> primes = IntStream.range(1, n).filter(i -> !notPrimes.contains(i)).boxed().collect(Collectors.toList());
        return primes;
    }
}