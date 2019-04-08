package org.jorion.euler.problem;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jorion.euler.util.Utils;

/**
 * <p>
 * NOTE: This problem is a more challenging version of Problem 81.
 * </p>
 * <p>
 * The minimal path sum in the 5 by 5 matrix below, by starting in any cell in the left column and finishing in any cell
 * in the right column, and only moving up, down, and right, is indicated in red and bold; the sum is equal to 994.
 * </p>
 *
 * <pre>
 * | 131  673  <b>234  103  018</b> |
 * | <b>201  096  342</b>  965  150 |
 * | 630  803  746  422  111 |
 * | 537  669  497  121  956 |
 * | 805  732  524  037  331 |
 * </pre>
 * <p>
 * Find the minimal path sum, in matrix.txt (right click and "Save Link/Target As..."), a 31K text file containing a 80
 * by 80 matrix, from the left column to the right column.
 * </p>
 * Sums: 201 => (+96) 297 => (+342) 639 => (+234) 873 => (+103) 976 => (+18) 994
 */
public class Euler082
{
    // --- Constants ---
    private static final String SEP = ",";

    // --- Methods ---
    public static void main(String[] args)
            throws Exception
    {
        long res; // 260324
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
                    if (x == 0) {
                        cell.setVal(m[y][x]);
                        continue;
                    }

                    // north
                    if (y > 0) {
                        minVal = findMinVal(n[y - 1][x], minVal);
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

        res = n[0][size - 1].getVal();
        for (int y = 0; y < size; y++) {
            Cell c = n[y][size - 1];
            int val = c.getVal();
            if (val < res) {
                res = val;
            }
        }

        System.out.println("#loops: " + loop);
        return res;
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