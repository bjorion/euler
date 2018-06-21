package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to
 * 10 are given:
 * 
 * <pre>
 *     1/2 =   0.5
 *     1/3 =   0.(3)
 *     1/4 =   0.25
 *     1/5 =   0.2
 *     1/6 =   0.1(6); 10/6 = 1.(6); 100/6 = 16.(6)
 *     1/7 =   0.(142857); 1_000_000/7 = 142857.(142857)
 *     1/8 =   0.125
 *     1/9 =   0.(1)
 *     1/10=   0.1
 * </pre>
 * 
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring
 * cycle.
 * <p>
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
public class Euler026
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1000;
        long res; // 983
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Simple ", res, delta);
    }

    /**
     * https://www.mathblog.dk/project-euler-26-find-the-value-of-d-1000-for-which-1d-contains-the-longest-recurring-cycle/
     */
    private static long calc1(int max)
    {
        int sequenceLength = 0;
        int res = 0;
        
        for (int i = max; i > 1; i--) {
            if (sequenceLength >= i) {
                break;
            }
         
            int[] foundRemainders = new int[i];
            int value = 1;
            int position = 0;
         
            while (foundRemainders[value] == 0 && value != 0) {
                foundRemainders[value] = position;
                value *= 10;
                value %= i;
                position++;
            }
         
            if (position - foundRemainders[value] > sequenceLength) {
                sequenceLength = position - foundRemainders[value];
                res = i;
            }
        }
        // System.out.println(sequenceLength);
        return res;
    }

}