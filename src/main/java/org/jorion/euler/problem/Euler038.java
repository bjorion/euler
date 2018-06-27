package org.jorion.euler.problem;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 * 
 * <pre>
 * 192 × 1 = 192 
 * 192 × 2 = 384 
 * 192 × 3 = 576
 * </pre>
 * 
 * By concatenating each product we get the 1 to 9 pandigital, 192_384_576. We will call 192384576 the concatenated
 * product of 192 and (1,2,3)
 * <p>
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918_273_645,
 * which is the concatenated product of 9 and (1,2,3,4,5).
 * <p>
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer
 * with (1,2, ... , n) where n > 1?
 */
public class Euler038
{

    // --- Methods ---
    public static void main(String[] args)
    {
        long res; // 932718654
        long delta;

        Utils.start();
        res = calc1();
        delta = Utils.stop();
        Utils.print("Simple ", res, delta);
    }

    /**
     * Check 9, then 99-90, then 999-900, then 9999-9000.
     */
    private static long calc1()
    {
        int res = 0;
        int max = 0;
        int min = 9;

        for (int i = 0; i < 4; i++) {
            max = max * 10 + 9;
            for (int val = max; val >= min; val--) {
                if (!MathUtils.isUnique(val, false)) {
                    continue;
                }
                String str = "";
                int count = 0;
                while (str.length() < 9) {
                    count++;
                    str += Integer.toString(val * count);
                }
                if (str.length() == 9) {
                    int n = Integer.parseInt(str);
                    if (MathUtils.isPandigital(n)) {
                        if (n > res) {
                            // System.out.println("val: " + val);
                            res = n;
                        }
                    }
                }
            }
            min *= 10;
        }

        return res;
    }

}