package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jorion.euler.util.Utils;

/**
 * We define the Matrix Sum of a matrix as the maximum sum of matrix elements with each element being the only one in
 * his row and column. For example, the Matrix Sum of the matrix below equals 3315 ( = 863 + 383 + 343 + 959 + 767) = (
 * 959 + 863 + 767 + 383 + 343 )
 *
 * <pre>
 *   7  53 183 439 863
 * 497 383 563  79 973
 * 287  63 343 169 583
 * 627 343 773 959 943
 * 767 473 103 699 303
 * </pre>
 *
 * Find the Matrix Sum of:
 *
 * <pre>
 *   7  53 183 439 863 497 383 563  79 973 287  63 343 169 583
 * 627 343 773 959 943 767 473 103 699 303 957 703 583 639 913
 * 447 283 463  29  23 487 463 993 119 883 327 493 423 159 743
 * 217 623   3 399 853 407 103 983  89 463 290 516 212 462 350
 * 960 376 682 962 300 780 486 502 912 800 250 346 172 812 350
 * 870 456 192 162 593 473 915  45 989 873 823 965 425 329 803
 * 973 965 905 919 133 673 665 235 509 613 673 815 165 992 326
 * 322 148 972 962 286 255 941 541 265 323 925 281 601  95 973
 * 445 721  11 525 473  65 511 164 138 672  18 428 154 448 848
 * 414 456 310 312 798 104 566 520 302 248 694 976 430 392 198
 * 184 829 373 181 631 101 969 613 840 740 778 458 284 760 390
 * 821 461 843 513  17 901 711 993 293 157 274  94 192 156 574
 *  34 124   4 878 450 476 712 914 838 669 875 299 823 329 699
 * 815 559 813 459 522 788 168 586 966 232 308 833 251 631 107
 * 813 883 451 509 615  77 281 613 459 205 380 274 302  35 805
 * </pre>
 */
public class Euler345
{
    // @formatter off
    @SuppressWarnings("unused")
    private static final int[][] M0 = { { 7, 53, 183, 439, 863 }, { 497, 383, 563, 79, 973 }, { 287, 63, 343, 169, 583 }, { 627, 343, 773, 959, 943 },
            { 767, 473, 103, 699, 303 } };

    private static final int[][] M = { { 7, 53, 183, 439, 863, 497, 383, 563, 79, 973, 287, 63, 343, 169, 583 },
            { 627, 343, 773, 959, 943, 767, 473, 103, 699, 303, 957, 703, 583, 639, 913 },
            { 447, 283, 463, 29, 23, 487, 463, 993, 119, 883, 327, 493, 423, 159, 743 },
            { 217, 623, 3, 399, 853, 407, 103, 983, 89, 463, 290, 516, 212, 462, 350 },
            { 960, 376, 682, 962, 300, 780, 486, 502, 912, 800, 250, 346, 172, 812, 350 },
            { 870, 456, 192, 162, 593, 473, 915, 45, 989, 873, 823, 965, 425, 329, 803 },
            { 973, 965, 905, 919, 133, 673, 665, 235, 509, 613, 673, 815, 165, 992, 326 },
            { 322, 148, 972, 962, 286, 255, 941, 541, 265, 323, 925, 281, 601, 95, 973 },
            { 445, 721, 11, 525, 473, 65, 511, 164, 138, 672, 18, 428, 154, 448, 848 },
            { 414, 456, 310, 312, 798, 104, 566, 520, 302, 248, 694, 976, 430, 392, 198 },
            { 184, 829, 373, 181, 631, 101, 969, 613, 840, 740, 778, 458, 284, 760, 390 },
            { 821, 461, 843, 513, 17, 901, 711, 993, 293, 157, 274, 94, 192, 156, 574 },
            { 34, 124, 4, 878, 450, 476, 712, 914, 838, 669, 875, 299, 823, 329, 699 },
            { 815, 559, 813, 459, 522, 788, 168, 586, 966, 232, 308, 833, 251, 631, 107 },
            { 813, 883, 451, 509, 615, 77, 281, 613, 459, 205, 380, 274, 302, 35, 805 } };
    // @formatter on

    // --- Methods ---
    public static void main(String[] args)
    {
        final int n = M.length;
        long res;
        long delta;

        Utils.start();
        res = calc1(n);
        delta = Utils.stop();
        Utils.print("MyAlgorithm     ", res, delta);

        Utils.start();
        res = calc2(n);
        delta = Utils.stop();
        Utils.print("From Euler site ", res, delta);
    }

    /**
     * My own algorithm.
     */
    private static long calc1(int n)
    {
        Set<Cell> set = new TreeSet<>();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                set.add(new Cell(M[y][x], y, x));
            }
        }
        // set.stream().forEach(System.out::println);
        List<Cell> list = new ArrayList<>();
        list.addAll(set);

        int max = 0;
        int sum = 0;
        // int count = 0;

        int listIndex = 0; // index for collection 'list'
        int arrIndex = 0; // index for arrays 'indexes, counts, arr'
        int[] indexes = new int[n];
        int[] counts = new int[n];
        Cell[] cells = new Cell[n];

        boolean done = false;
        while (!done) {
            // loop through the list to find compatible cells
            while (arrIndex < n) {
                // count++;
                if (listIndex >= n * n) {
                    break;
                }
                Cell cell = list.get(listIndex);
                // check if we can add the cell to the array
                if (cell.compatible(cells, arrIndex) == false) {
                    listIndex++;
                    continue;
                }
                indexes[arrIndex] = listIndex;
                counts[arrIndex]++;
                if (arrIndex + 1 < n) {
                    counts[arrIndex + 1] = 0;
                }
                cells[arrIndex] = cell;
                sum += cell.val;
                // end of loop: we check the value
                if (arrIndex == n - 1) {
                    if (sum > max) {
                        max = sum;
                        // Arrays.asList(cells).stream().forEach(System.out::println);
                        // System.out.println("Found: " + max);
                    }
                }
                arrIndex++;
                listIndex++;
                // stop looking when we are sure there cannot be any better solution downhill
                int maxPossible = sum + (n - arrIndex) * cell.val;
                if (maxPossible < max) {
                    break;
                }
            }

            // end of list: rewind
            while (true) {
                arrIndex--;
                if (arrIndex < 0) {
                    done = true;
                    break;
                }
                listIndex = indexes[arrIndex];
                sum -= cells[arrIndex].val;
                if (counts[arrIndex] < n - arrIndex) {
                    listIndex++;
                    break;
                }
            }
        }
        // System.out.println("count: " + count);

        return max;
    }

    /**
     * Algorithm from Project Euler forum.
     */
    private static long calc2(int n)
    {
        int[][] f = new int[n + 1][1 << n];
        for (int i = 0; i < n; ++i) {
            for (int b = 0; b < (1 << n); ++b) {
                for (int j = 0; j < n; ++j) {
                    if (((b >> j) & 1) == 0) {
                        f[i + 1][b | (1 << j)] = Math.max(f[i + 1][b | (1 << j)], f[i][b] + M[i][j]);
                    }
                }
            }
        }
        return f[n][(1 << n) - 1];
    }

    static class Cell implements Comparable<Cell>
    {
        int val;

        int y;

        int x;

        Cell(int val, int y, int x)
        {
            this.val = val;
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Cell other)
        {
            int value = Integer.compare(other.val, this.val);
            if (value == 0) {
                value = Integer.compare(this.y, other.y);
            }
            if (value == 0) {
                value = Integer.compare(this.x, other.x);
            }
            return value;
        }

        @Override
        public String toString()
        {
            return "Cell [" + val + " (" + y + ", " + x + ")]";
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + val;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Cell other = (Cell) obj;
            if (val != other.val) {
                return false;
            }
            if (x != other.x) {
                return false;
            }
            if (y != other.y) {
                return false;
            }
            return true;
        }

        public boolean compatible(Cell[] arr, int arrIndex)
        {
            boolean ok = true;
            for (int i = 0; i < arrIndex; i++) {
                if (sameLine(arr[i])) {
                    ok = false;
                    break;
                }
            }
            return ok;
        }

        public boolean sameLine(Cell other)
        {
            return other.x == this.x || other.y == this.y;
        }
    }
}