package org.jorion.euler.problem;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * <p>
 * What is the 10 001st prime number?
 */
public class Euler007
{
    public static void main(String[] args)
    {
        final int max = 10_001;

        long res; // 104743
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Simple", res, delta);
    }

    private static long calc1(int max)
    {
        long res = 0;
        long i = 3;
        int count = 1;
        while (count < max) {
            if (PrimeUtils.isPrime(i)) {
                res = i;
                count++;
            }
            i += 2;
        }
        return res;
    }
}
