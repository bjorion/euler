package org.jorion.euler.problem;

import java.math.BigInteger;
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
        long res;
        long delta;

        List<String> lines = Utils.readLines("p099_base_exp.txt");

        Utils.start();
        res = calc1(lines);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static long calc1(List<String> lines)
    {
        int size = lines.size();
        List<int[]> list = new ArrayList<>(size);
        List<String[]> strings = new ArrayList<>(size);

        // parse the lines
        for (int i = 0; i < lines.size(); i++) {
            String[] numbers = lines.get(i).split(",");
            strings.add(numbers);
            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);
            list.add(new int[] { a, b });
        }

        // remove lines where both numbers are smaller than numbers on another line
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] ab = list.get(i);
            if (ab != null) {
                for (int j = i + 1; j < list.size(); j++) {
                    int[] cd = list.get(j);
                    if (cd != null) {
                        if (cd[0] <= ab[0] && cd[1] <= ab[1]) {
                            // System.out.println("removing index: " + i + ":" + j);
                            list.set(j, null); // cd = null
                            count++;
                        }
                        if (cd[0] > ab[0] && cd[1] > ab[1]) {
                            list.set(i, null); // ab = null
                            // System.out.println("removing index: " + i + ":" + j);
                            count++;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(count);

        int index = -1;
        BigInteger best = BigInteger.ZERO;
        for (int i = 0; i < list.size(); i++) {
            int[] ab = list.get(i);
            if (ab != null) {
                System.out.println(i);
                String[] numbers = strings.get(i);
                BigInteger bi = new BigInteger(numbers[0]).pow(ab[1]);
                if (bi.compareTo(best) > 0) {
                    best = bi;
                    index = i;
                }
            }
        }

        return index;
    }

}