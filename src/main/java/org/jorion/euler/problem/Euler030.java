package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
 * 
 * <pre>
 *    1634 = 1^4 + 6^4 + 3^4 + 4^4
 *    8208 = 8^4 + 2^4 + 0^4 + 8^4
 *    9474 = 9^4 + 4^4 + 7^4 + 4^4
 * </pre>
 * 
 * As 1 = 1^4 is not a sum it is not included.
 * <p>
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 * <p>
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
public class Euler030
{
    // --- Constants ---
    private static final int ASCII_0 = 48;

    // --- Methods ---
    public static void main(String[] args)
    {
        final int power = 5;

        long res;
        long delta;

        Utils.start();
        res = calc1(power);
        delta = Utils.stop();
        Utils.print("Simple  ", res, delta);
        
        Utils.start();
        res = calc2(power);
        delta = Utils.stop();
        Utils.print("Simple 2", res, delta);
    }

    /**
     * Use Integer.toString()
     */
    private static long calc1(int power)
    {
        int max = 1_000_000;
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)Math.pow(i, power);
        }
        
        long sum = 0;
        for (int i = 10; i < max; i++) {
            long val = 0;
            String str = Integer.toString(i);
            for (int j = 0; j < str.length(); j++) {
                int digit = str.charAt(j) - ASCII_0;
                val += arr[digit];
            }
            if (i == val) {
                sum+= val;
                // System.out.println(val);
            }
        }
        return sum;
    }
    
    /**
     * Use modulo (faster).
     */
    private static long calc2(int power)
    {
        int max = 1_000_000;
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)Math.pow(i, power);
        }
        
        long sum = 0;
        for (int i = 10; i < max; i++) {
            long val = 0;
            int n = i;
            while (n > 0) {
                int digit = n % 10;
                val += arr[digit];
                n = n / 10;
            }
            if (i == val) {
                sum+= val;
            }
        }
        return sum;
    }
}
