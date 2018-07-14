package org.jorion.euler.problem;

import java.util.List;

import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

import static org.jorion.euler.util.WordUtils.ROM_LETTERS;
import static org.jorion.euler.util.WordUtils.ROM_DIGITS;

/**
 * sFor a number written in Roman numerals to be considered valid there are basic rules which must be followed. Even
 * though the rules allow some numbers to be expressed in more than one way there is always a "best" way of writing a
 * particular number.
 * <p>
 * For example, it would appear that there are at least six ways of writing the number sixteen:
 * 
 * <pre>
 * IIIIIIIIIIIIIIII
 * VIIIIIIIIIII
 * VVIIIIII
 * XIIIIII
 * VVVI
 * XVI
 * </pre>
 * 
 * However, according to the rules only XIIIIII and XVI are valid, and the last example is considered to be the most
 * efficient, as it uses the least number of numerals.
 * <p>
 * The 11K text file, roman.txt (right click and 'Save Link/Target As...'), contains one thousand numbers written in
 * valid, but not necessarily minimal, Roman numerals; see About... Roman Numerals for the definitive rules for this
 * problem.
 * <p>
 * Find the number of characters saved by writing each of these in their minimal form.
 * <p>
 * Note: You can assume that all the Roman numerals in the file contain no more than four consecutive identical units.
 */
public class Euler089
{

    // --- Methods ---
    public static void main(String[] args)
            throws Exception
    {
        long res; // 743
        long delta;

        List<String> romans = Utils.readLines("p089_roman.txt");

        Utils.start();
        res = calc1(romans);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static long calc1(List<String> romans)
    {
        int res = 0;
        for (String roman : romans) {
            int val = 0;
            int len = roman.length();
            for (int i = 0; i < len; i++) {
                String letter1 = roman.substring(i, i + 1);
                String letter2 = (i < len - 1) ? roman.substring(i, i + 2) : null;
                int index1 = findIndex(letter1);
                int index2 = findIndex(letter2);
                if (index2 == -1 || index1 < index2) {
                    val += ROM_DIGITS[index1];
                }
                else {
                    val += ROM_DIGITS[index2];
                    i++;
                }
            }
            res += (len - WordUtils.toRoman(val).length());
        }
        return res;
    }

    private static int findIndex(String letter)
    {
        int res = -1;
        if (letter != null) {
            for (int i = 0; i < ROM_LETTERS.length; i++) {
                if (ROM_LETTERS[i].equals(letter)) {
                    res = i;
                    break;
                }
            }
        }
        return res;
    }
}