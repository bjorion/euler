package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2,
 * 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The
 * lexicographic permutations of 0, 1 and 2 are:
 * 
 * <pre>
 * 012 021 102 120 201 210
 * </pre>
 * 
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class Euler024
{
    // --- Constants ---
    private static final int MAX = 1_000_000;

    // --- Variables ---
    private static int count = 0;
    
    private static String res;

    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 20;
        String res;
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);

    }

    private static String calc1(int max)
    {
        String str = "0123456789";
        permutate("", str);
        // System.out.println("count: " + count);
        return res;
    }

    public static boolean permutate(String prefix, String str)
    {
        boolean cont = true;
        int n = str.length();
        if (n == 0) {
            // System.out.println(prefix);
            count++;
            if (count == MAX) {
                cont = false;
                res = prefix;
            }
        }
        else {
            for (int i = 0; i < n && cont; i++) {
                cont = permutate(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
        return cont;
    }

}