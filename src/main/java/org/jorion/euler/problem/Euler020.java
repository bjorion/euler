package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * <p>
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800, <br>
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * <p>
 * Find the sum of the digits in the number 100!
 */
public class Euler020
{
    public static void main(String[] args)
    {
        final int max = 50;
        long res;
        long delta;

        Utils.start();
        res = addDigits(calc1(max));
        delta = Utils.stop();
        Utils.print("Brute force", res, delta);
    }

    private static long calc1(int max)
    {
        long p = 1;
        for (int i= 2; i <= max; i++) {
            p *= i;
            while (p % 10 == 0) {
                p /= 10;
                System.out.println("reduction - p = " + p + ", i = " + i);
            }
        }
        System.out.println(p);
        return p;
    }
    
    private static long addDigits(long n) {
        
        System.out.println("n = " + n);
        long res = 0;
        while (n > 0) {
            res += (n % 10);
            n /= 10;
        }
        return res;
    }
}
