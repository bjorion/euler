package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.List;

import org.jorion.euler.util.Utils;

/**
 * Comparing two numbers written in index form like 2^11 and 3^7 is not difficult, as any calculator would confirm that
 * 2^11 = 2048 < 3^7 = 2187.
 * <p>
 * However, confirming that 632382^518061 > 519432^525806 would be much more difficult, as both numbers contain over
 * three million digits.
 * <p>
 * Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text file containing one thousand lines with a
 * base/exponent pair on each line, determine which line number has the greatest numerical value.
 * <p>
 * NOTE: The first two lines in the file represent the numbers in the example given above.
 */
public class Euler099
{
    // --- Methods ---
    public static void main(String[] args)
            throws Exception
    {
        long res; // 709
        long delta;

        List<String> lines = Utils.readLines("p099_base_exp.txt");

        Utils.start();
        res = calc1(lines);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static long calc1(List<String> lines)
    {
        final int logBase = 0;
        final int exp = 1;

        int size = lines.size();
        List<double[]> list = new ArrayList<>(size);

        // parse the lines
        for (int i = 0; i < lines.size(); i++) {
            String[] numbers = lines.get(i).split(",");
            double a = Math.log(Integer.parseInt(numbers[0]));
            double b = Integer.parseInt(numbers[1]);
            list.add(new double[] { a, b }); // log(base), exponent
        }

        int index = 0;
        double highest = list.get(0)[exp] * list.get(0)[logBase];
        for (int i = 1; i < list.size(); i++) {
            double cur = list.get(i)[exp] * list.get(i)[logBase];
            if (cur > highest) {
                highest = cur;
                index = i;
                // System.out.println(index + ":" + cur);
            }
        }

        return index + 1;
    }

}