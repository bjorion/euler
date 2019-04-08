package org.jorion.euler.problem;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jorion.euler.util.Utils;

/**
 * <p>
 * NOTE: This problem is a more challenging version of Problem 81.
 * </p>
 * <p>
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by moving left, right, up,
 * and down, is indicated in bold red and is equal to 2297.
 * </p>
 *
 * <pre>
 * |_131_ 673 _234__103__018_|
 * |_201__096__342  965 _150_|
 * | 630  803  746 _422__111_|
 * | 537  669  497 _121_ 956 |
 * | 805  732  524 _037__331_|
 * </pre>
 * <p>
 * Find the minimal path sum, in matrix.txt (right click and "Save Link/Target As..."), a 31K text file containing a 80
 * by 80 matrix, from the left column to the right column.
 * </p>
 * Sums: 131 + 201(332) + 96(428) + 342(770) + 234(1004) + 103(1107) + 18(1125) + 150(1275) + 111(1386) + 422(1808) +
 * 121(1929) + 37(1966) + 331 = 2297
 */
public class Euler083
{
    // --- Constants ---
    private static final String SEP = ",";

    // --- Methods ---
    public static void main(String[] args)
            throws Exception
    {
        long res; // 425185
        long delta;

        List<String> lines = Utils.readLines("p082_matrix.txt");
        // convert the collection of lines to a matrix
        int size = lines.size();
        int[][] m = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] numbers = lines.get(i).split(SEP);
            for (int j = 0; j < size; j++) {
                m[i][j] = Integer.parseInt(numbers[j].trim());
            }
        }

        Utils.start();
        res = calc1(m);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static long calc1(int[][] m)
    {
        int res = -1;
        int size = m.length;
        Cell[][] n = new Cell[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                n[y][x] = new Cell();
            }
        }

        int loop = 0;
        boolean done = false;
        while (!done) {
            loop++;
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    int minVal = -1;
                    Cell cell = n[y][x];

                    // first column
                    if (x == 0 && y == 0) {
                        cell.setVal(m[y][x]);
                        continue;
                    }

                    // north
                    if (y > 0) {
                        minVal = findMinVal(n[y - 1][x], minVal);
                    }

                    // east
                    if (x < size - 1) {
                        minVal = findMinVal(n[y][x + 1], minVal);
                    }

                    // south
                    if (y < size - 1) {
                        minVal = findMinVal(n[y + 1][x], minVal);
                    }

                    // west
                    if (x > 0) {
                        minVal = findMinVal(n[y][x - 1], minVal);
                    }

                    if (minVal >= 0) {
                        cell.setVal(m[y][x] + minVal);
                    }
                }
            }

            done = true;
            int sum = 0;
            for (int y = 0; y < size; y++) {
                Cell c = n[y][size - 1];
                int val = c.getVal();
                sum += val;
            }
            if (res == -1 || sum < res) {
                done = false;
                res = sum;
            }

            // printCells(n);
        }

        System.out.println("#loops: " + loop);
        return n[size - 1][size - 1].getVal();
    }

    private static int findMinVal(Cell c, int minVal)
    {
        int val = c.getVal();
        if (val >= 0) {
            minVal = (val <= minVal || minVal == -1) ? val : minVal;
        }
        return minVal;
    }

    @SuppressWarnings("unused")
    private static void printCells(Cell[][] n)
    {
        System.out.println("-----------------");
        int size = n.length;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                String str = StringUtils.leftPad(Integer.toString(n[y][x].getVal()), 5);
                System.out.print(str);
            }
            System.out.println();
        }
    }

    static class Cell
    {
        private int val = -1;

        int getVal()
        {
            return val;
        }

        void setVal(int val)
        {
            this.val = val;
        }
    }

}