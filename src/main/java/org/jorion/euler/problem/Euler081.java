package org.jorion.euler.problem;

import java.util.List;

import org.jorion.euler.util.Utils;

/**
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by only moving to the right
 * and down, is indicated in bold red and is equal to 2427.
 * 
 * <pre>
 * |_131_ 673  234  103   18 |
 * |_201__096__342_ 965  150 |
 * | 630  803 _746__422_ 111 |
 * | 537  669  497 _121_ 956 |
 * | 805  732  524 _037__331_|
 * </pre>
 * 
 * Find the minimal path sum, in matrix.txt (right click and "Save Link/Target As..."), a 31K text file containing a 80
 * by 80 matrix, from the top left to the bottom right by only moving right and down.
 */
public class Euler081
{
    // --- Constants ---
    private static final String SEP = ",";

    // --- Methods ---
    public static void main(String[] args)
            throws Exception
    {
        long res; // 427337
        long delta;

        List<String> lines = Utils.readLines("p081_matrix.txt");
        // convert the collection of lines to a matrix
        int size = lines.size();
        int[][] m = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] numbers = lines.get(i).split(SEP);
            for (int j = 0; j < size; j++) {
                m[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        Utils.start();
        res = calc1(m);
        delta = Utils.stop();
        Utils.print("Algorithm - Array  ", res, delta);
        
        Utils.start();
        res = calc2(m);
        delta = Utils.stop();
        Utils.print("Algorithm - Matrix ", res, delta);
    }

    // same logic than Euler067
    private static long calc1(int[][] m)
    {
        int size = m.length;

        // loop through the matrix
        // on each step, we keep the best result
        // (we do not keep the path)
        int sums[] = new int[0];
        int sizeDiagonal = 0;
        for (int step = 0; step < 2 * size - 1; step++) {
            sizeDiagonal += (step < size) ? 1 : -1;
            int sums2[] = new int[sizeDiagonal];
            // we work on the diagonal
            int i0 = (step < size) ? step : size - 1;
            for (int k = 0; k < sizeDiagonal; k++) {
                int i = i0 - k;
                int j = step - i;
                int currentVal = m[i][j];
                int leftVal = getPreviousSum(sums, size, i, j - 1);
                int topVal = getPreviousSum(sums, size, i - 1, j);
                // we add the smallest value of leftVal and topVal
                // ignoring "undefined" values (-1)
                if (leftVal > 0 && topVal > 0) {
                    currentVal += (leftVal < topVal) ? leftVal : topVal;
                }
                else if (leftVal > 0 && topVal < 0) {
                    currentVal += leftVal;
                }
                else if (leftVal < 0 && topVal > 0) {
                    currentVal += topVal;
                }
                sums2[k] = currentVal;
            }
            sums = sums2;
        }
        return sums[0];
    }

    // same logic than Euler067
    private static long calc2(int[][] m)
    {
        int size = m.length;
        int[][] n = new int[size][size];

        // loop through the matrix
        // on each step, we keep the best result
        int sizeDiagonal = 0;
        for (int step = 0; step < 2 * size - 1; step++) {
            sizeDiagonal += (step < size) ? 1 : -1;
            // we work on the diagonal
            int i0 = (step < size) ? step : size - 1;
            for (int k = 0; k < sizeDiagonal; k++) {
                int i = i0 - k;
                int j = step - i;
                int currentVal = m[i][j];
                int leftVal = getMin(n, i, j - 1);
                int topVal = getMin(n, i - 1, j);
                // we add the smallest value of leftVal and topVal
                // ignoring "undefined" values (-1)
                if (leftVal > 0 && topVal > 0) {
                    currentVal += (leftVal < topVal) ? leftVal : topVal;
                }
                else if (leftVal > 0 && topVal < 0) {
                    currentVal += leftVal;
                }
                else if (leftVal < 0 && topVal > 0) {
                    currentVal += topVal;
                }
                n[i][j] = currentVal;
            }
        }
        return n[size - 1][size - 1];
    }

    private static int getPreviousSum(int[] sums, int size, int i, int j)
    {
        int val = -1;
        int index = (i + j < size) ? j : size - i - 1;
        if (index >= 0 && index < sums.length) {
            val = sums[index];
        }
        return val;
    }

    private static int getMin(int[][] n, int i, int j)
    {
        int val = -1;
        if (i >= 0 && j >= 0) {
            val = n[i][j];
        }
        return val;
    }

}