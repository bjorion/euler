package org.jorion.euler.problem;

import java.util.HashSet;
import java.util.Set;

import org.jorion.euler.util.Utils;

/**
 * The number 7 is special, because 7 is 111 written in base 2, and 11 written in base 6 (i.e. 7<sub>10</sub> =
 * 11<sub>6</sub> = 111<sub>2</sub>). In other words, 7 is a repunit in at least two bases b > 1.
 * <p>
 * We shall call a positive integer with this property a strong repunit. It can be verified that there are 8 strong
 * repunits below 50: {1,7,13,15,21,31,40,43} (sum = 171). Furthermore, the sum of all strong repunits below 1000 equals
 * 15864.
 * <p>
 * Find the sum of all strong repunits below 10^12.
 */
public class Euler346
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final long max = 1_000_000_000_000L;
        long res; // 336_108_797_689_259_276
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);

        Utils.start();
        res = calc2(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    /**
     * All numbers are repunit since it's always possible to write n = (n - 1) + 1 = <b>11</b><sub>n-1</sub>.
     * <p>
     * We start with the value {@code 111}, and we try all the possible bases {@code b}. The sum is 1.b^2 + 1.b^1 +
     * 1.b^0. We save the sum in a set (to avoid duplicates) until it's over the maximum value requested.
     * <p>
     * Once it's done, we proceed with the value {@code 1111} and so on until nothing is added in the set anymore
     * (meaning even in base 2 we are above the maximum value).
     */
    private static long calc1(long max)
    {
        Set<Long> res = new HashSet<>();

        int n = 3;
        boolean done = false;
        while (!done) {
            // System.out.println("n: " + n);
            done = true;
            int b = 2;
            while (true) {
                long val = 1;
                long acc = val;
                for (int i = 1; i < n; i++) {
                    acc = acc + (val * b);
                    val *= b;
                }
                if (acc >= max) {
                    break;
                }
                // System.out.println("n: " + n + ", base: " + b + ", acc: " + acc);
                res.add(acc);
                done = false;
                b++;
            }
            n++;
        }
        long sum = res.stream().reduce(0L, Long::sum);
        return sum + 1;
    }

    /**
     * Without using a Set: about 70x faster
     */
    private static long calc2(long max)
    {
        long sum = 0;
        int n = 3;
        boolean done = false;
        while (!done) {
            // System.out.println("n: " + n);
            done = true;
            int b = 2;
            while (true) {
                long val = 1;
                long acc = val;
                for (int i = 1; i < n; i++) {
                    acc = acc + (val * b);
                    val *= b;
                }
                if (acc >= max) {
                    break;
                }
                sum += acc;
                done = false;
                b++;
            }
            n++;
        }
        // https://en.wikipedia.org/wiki/Goormaghtigh_conjecture
        return sum + 1 - (31 + 8191);
    }
}