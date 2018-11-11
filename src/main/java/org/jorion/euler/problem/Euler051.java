package org.jorion.euler.problem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jorion.euler.util.PrimeUtils;
import org.jorion.euler.util.Utils;

/**
 * By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43,
 * 53, 73, and 83, are all prime.
 * <p>
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having
 * seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and
 * 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.
 * <p>
 * Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit,
 * is part of an eight prime value family.
 */
public class Euler051
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int len = 8;
        String res; // 121313, pattern: _2_3_3
        long delta;

        Utils.start();
        res = calc1(len);
        delta = Utils.stop();
        Utils.print("Simple Algorithm ", res, delta);
    }

    /**
     * family: 121313 222323 323333 424343 525353 626363 828383 929393
     */
    private static String calc1(int length)
    {
        Map<String, LinkedList<String>> map = new HashMap<>();

        int start = 100_000;
        int end = start * 10;
        boolean[] primes = PrimeUtils.isPrimeSoE(end);

        String pattern = null;
        LinkedList<String> family = null;

        for (int i = start + 1; i < end && family == null; i += 2) {
            // ignore non-prime
            if (!primes[i]) {
                continue;
            }
            String si = Integer.toString(i);
            // with 1 or 2 recurring digits, there can be only up to 7 numbers in the family
            // for 8 numbers, you need at least 3 recurring digits
            // (if the sum of the digits = 3, then it's not a prime)
            if (!hasAtLeastRecurringDigit(si, 3)) {
                continue;
            }

            for (int j = start + 1; j < i; j += 2) {
                if (!primes[j]) {
                    continue;
                }

                // here: i and j are primes
                String sj = Integer.toString(j);
                pattern = findCommonPattern(si, sj);
                if (pattern != null) {
                    LinkedList<String> list = map.get(pattern);
                    if (list != null) {
                        if (!list.getLast().equals(si)) {
                            list.add(si);
                        }
                    }
                    else {
                        list = new LinkedList<>();
                        list.add(sj);
                        list.add(si);
                        map.put(pattern, list);
                    }
                    if (list.size() == length) {
                        family = list;
                        break;
                    }
                }
            }
        }
        // System.out.println("Pattern: " + pattern);
        // family.stream().forEach(System.out::println);
        return (pattern != null) ? family.get(0) : null;
    }

    private static boolean hasAtLeastRecurringDigit(String s, int n)
    {
        boolean ok = false;
        int[] arr = new int[10];
        for (int i = 0; i < s.length(); i++) {
            int digit = (s.charAt(i) - '0');
            arr[digit]++;
            if (arr[digit] >= n) {
                ok = true;
                break;
            }
        }
        return ok;
    }

    private static String findCommonPattern(String a, String b)
    {
        if (a.length() != b.length()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean result = true;
        boolean first = true;
        char a1 = '?', b1 = '?';
        for (int i = 0; i < a.length(); i++) {
            char a2 = a.charAt(i);
            char b2 = b.charAt(i);
            // same character, we skip
            if (a2 == b2) {
                sb.append(a2);
                continue;
            }
            sb.append("_");
            // first diffent character, we memorize
            if (first) {
                a1 = a2;
                b1 = b2;
                first = false;
            }
            // second different character, we compare
            else if (a2 != a1 || b2 != b1) {
                result = false;
                break;
            }
        }
        return result ? sb.toString() : null;
    }

}