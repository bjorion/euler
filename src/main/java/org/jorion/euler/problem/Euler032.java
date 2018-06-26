package org.jorion.euler.problem;

import java.util.HashSet;
import java.util.Set;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example,
 * the 5-digit number, 15234, is 1 through 5 pandigital.
 * <p>
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1
 * through 9 pandigital.
 * <p>
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9
 * pandigital.
 * <p>
 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
public class Euler032
{
    // --- Methods ---
    public static void main(String[] args)
    {
        long res; // 45228
        long delta;

        Utils.start();
        res = calc1();
        delta = Utils.stop();
        Utils.print("Optimized ", res, delta);
    }

    private static long calc1()
    {
        int res = 0;
        Set<Integer> set = new HashSet<>();

        // min2 = 1; 10
        for (int i = 1; i <= 2; i++) {
            int min1 = 1;
            for (int j = 2; j <= i; j++) {
                min1 *= 10;
            }
            int max1 = min1 * 10; // 10; 100
            int min2 = 10_000 / max1; // 1000; 100

            for (int multiplicand = min1; multiplicand < max1; multiplicand++) {
                // System.out.println("multiplicand: " + multiplicand);
                if (!MathUtils.isUnique(multiplicand, false)) {
                    continue;
                }
                int multiplier = min2;
                int product;
                while ((product = multiplicand * multiplier) < 10_000) {
                    if (MathUtils.isUnique(multiplier, false)) {
                        if (MathUtils.isUnique(product, false)) {
                            String str = Integer.toString(multiplicand) + Integer.toString(multiplier) + Integer.toString(product);
                            if (str.length() == 9 && MathUtils.isPandigital(Integer.parseInt(str))) {
                                // System.out.println(multiplicand + " * " + multiplier + " = " + product);
                                set.add(product);
                            }
                        }
                    }
                    multiplier++;
                }
            }
        }
        for (int product : set) {
            res += product;
        }
        return res;
    }

}
