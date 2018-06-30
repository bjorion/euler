package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i)
 * each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 * <p>
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is
 * one other 4-digit increasing sequence.
 * <p>
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 */
public class Euler049
{
    // --- Methods ---
    public static void main(String[] args)
    {
        String res;
        long delta;

        Utils.start();
        res = calc1();
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static String calc1()
    {
        final int min = 1_000;
        final int max = 10_000;
        boolean[] primes = PrimeUtils.isPrimeSoE(max - 1);
        // ignore 1-,2- and 3-digit numbers
        for (int i = 0; i < min; i++) {
            primes[i] = false;
        }
        List<String> fingerprints = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int prime = min; prime < max; prime++) {
            prime = Utils.nextTrue(primes, prime);
            // we are at the end of the list
            if (prime < 0) {
                break;
            }
            int[] digits = intToDigits(prime);
            Arrays.sort(digits);
            String str = Arrays.toString(digits);
            // we already met a permutation of this number
            if (fingerprints.contains(str)) {
                continue;
            }
            fingerprints.add(str);

            List<Integer> permutations = new ArrayList<>();
            permutate("", digitsToString(digits), permutations, primes);
            if (permutations.size() > 2) {
                for (int i = 0; i < permutations.size() - 2; i++) {
                    for (int j = i + 1; j < permutations.size() - 1; j++) {
                        int diff = permutations.get(j) - permutations.get(i);
                        int next = permutations.get(j) + diff;
                        if (permutations.contains(next)) {
                            sb.append(permutations.get(i)).append(permutations.get(j)).append(next);
                            sb.append(" ");
                        }
                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * Generate permutations of the given string and store them in the permutations list.
     */
    public static void permutate(String prefix, String str, List<Integer> permutations, boolean[] primes)
    {
        int n = str.length();
        if (n == 0) {
            int val = Integer.parseInt(prefix);
            // System.out.println(prefix);
            if (primes[val] && !permutations.contains(val)) {
                permutations.add(val);
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                permutate(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), permutations, primes);
            }
        }
        return;
    }

    /**
     * Convert a four-digit number to an array of digits.
     */
    private static int[] intToDigits(int n)
    {
        final int size = 4;
        int[] digits = new int[size];
        int index = size;
        while (n > 0) {
            digits[--index] = n % 10;
            n /= 10;
        }
        return digits;
    }

    /**
     * @param digits an array of digits
     * @return the array converted to a string
     */
    private static String digitsToString(int[] digits)
    {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }
        return sb.toString();
    }

}