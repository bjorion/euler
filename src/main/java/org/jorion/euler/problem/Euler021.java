package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n). If d(a) = b
 * and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 * <p>
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The
 * proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * <p>
 * Evaluate the sum of all the amicable numbers under 10000.
 */
public class Euler021
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 10_000;
        long res; // 31626
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Array (best)", res, delta);

        Utils.start();
        res = calc2(max);
        delta = Utils.stop();
        Utils.print("Direct      ", res, delta);
    }

    private static long calc1(int max)
    {
        long res = 0;
        int[] array = new int[max];
        for (int i = 2; i < max; i++) {
            int sum = sumDivisors(i);
            array[i] = sum;
        }
        for (int i = 2; i < max; i++) {
            int sum = array[i];
            if (sum != i && sum > 2 && sum < max) {
                if (array[sum] == i) {
                    // System.out.println(i + " => " + sum);
                    res += i;
                }
            }
        }

        return res;
    }

    private static long calc2(int max)
    {
        long res = 0;
        for (int i = 2; i < max; i++) {
            int sum = sumDivisors(i);
            if (i != sum && sumDivisors(sum) == i) {
                res += i;
            }
        }
        return res;
    }

    /**
     * @return the sum of proper divisors of n
     */
    private static int sumDivisors(int n)
    {
        int sum = 1;
        int factor = 2;
        int max = (int) Math.sqrt(n);
        while (factor <= max) {
            if (n % factor == 0) {
                sum += factor;
                int factor2 = n / factor;
                if (factor2 != factor) {
                    sum += factor2;
                }
            }
            factor++;
        }

        return sum;
    }
}
