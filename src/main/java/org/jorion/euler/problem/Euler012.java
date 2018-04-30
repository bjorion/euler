package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 +
 * 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:
 * 
 * <pre>
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * </pre>
 * 
 * Let us list the factors of the first seven triangle numbers:
 * 
 * <pre>
 *      1: 1
 *      3: 1,3
 *      6: 1,2,3,6
 *     10: 1,2,5,10
 *     15: 1,3,5,15
 *     21: 1,3,7,21
 *     28: 1,2,4,7,14,28
 * </pre>
 * 
 * We can see that 28 is the first triangle number to have over five divisors.
 * <p>
 * What is the value of the first triangle number to have over five hundred divisors?
 */
public class Euler012
{
    public static void main(String[] args)
    {
        final int max = 500;
        int res; // 76576500
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Brute Force    ", res, delta);

        Utils.start();
        res = calc2(max);
        delta = Utils.stop();
        Utils.print("Advanced (best)", res, delta);
    }

    private static int calc1(int max)
    {
        int res = 0;
        int sum = 1;
        int n = 2;
        while (true) {
            sum += n;
            int d = countDivisors(sum);
            // System.out.println(sum + ": " + d);
            if (d > max) {
                res = sum;
                break;
            }
            n++;
        }
        return res;
    }

    private static int countDivisors(int n)
    {
        int d = 2; // 1 and n
        int factor = 2;
        int max = (int) Math.sqrt(n);
        while (factor <= max) {
            if (n % factor == 0) {
                d += 2;
            }
            factor++;
        }
        return d;
    }

    /**
     * Number p = n * (n+1) / 2. n and (n+1) have no common divisors (except 1).
     */
    private static int calc2(int limit)
    {
        int answer = 0;
        int cnt = 0;
        for (int i = 1; cnt <= limit; i++) {
            if ((i & 0b1) == 0) {
                cnt = countDivisors(i >> 1) * countDivisors(i + 1);
            }
            else {
                cnt = countDivisors(i) * (countDivisors((i + 1) >> 1));
            }
            // System.out.println("" + i + "\t" + cnt);
            if (cnt > limit) {
                answer = i;
            }
        }
        return answer * (answer + 1) / 2;
    }

}
