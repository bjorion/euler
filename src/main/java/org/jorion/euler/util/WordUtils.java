package org.jorion.euler.util;

import java.util.Arrays;

public class WordUtils
{
    // --- Constants ---
    public static final int ASCII_0 = 48;

    public static final int[] ROM_DIGITS = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

    public static final String[] ROM_LETTERS = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

    static {
        assert ROM_DIGITS.length == ROM_LETTERS.length;
    }

    // --- Methods ---
    protected static void wakeUp()
    {}

    /**
     * @param val the number to reverse
     * @return the reversed given number
     */
    public static long reverse(long val)
    {
        String str = Long.toString(val);
        return Long.parseLong(reverse(str));
    }

    /**
     * Reverse the given string.
     *
     * @return the string reversed
     */
    public static String reverse(String str)
    {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Test if the given number is palindromic (without converting this number to a String).
     *
     * @param val the number to test
     * @return True if the given number is palindromic in base 10.
     * @throws Exception if number length is greater than 20
     */
    public static boolean isPalindromic(long val)
    {
        final int len = 20;
        int[] digits = new int[len];
        int index = 0;

        while (val > 0) {
            digits[index++] = (int) val % 10;
            val /= 10;
        }

        boolean flag = true;
        for (int i = 0; i < (index + 1) / 2; i++) {
            if (digits[i] != digits[index - i - 1]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * @return True if the given number is palindromic in base 10.
     */
    public static boolean isPalindromicStr(int val)
    {
        return isPalindromic(Integer.toString(val));
    }

    /**
     * @return True if the given string is palindromic.
     */
    public static boolean isPalindromic(String str)
    {
        int len = str.length();
        boolean flag = true;
        for (int i = 0; i < (len + 1) / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * @param digits an array of digits
     * @return the array converted to a string (leading 0 are skipped)
     */
    @Deprecated
    public static String digitToString(int[] digits)
    {
        int n = digits.length - 1;
        StringBuilder sb = new StringBuilder();
        while (n >= 0 && digits[n] == 0) {
            n--;
        }
        while (n >= 0) {
            sb.append(digits[n--]);
        }
        if (sb.length() == 0) {
            sb.append("0");
        }
        return sb.toString();
    }

    /**
     * Swap two characters inside a given String.
     *
     * @param a the string to act upon
     * @param i the index of the first character
     * @param j the index of the second character
     * @return the string with the two given characters swapped
     */
    public static String swap(String a, int i, int j)
    {
        String res = a;
        if (i != j) {
            char temp;
            char[] chars = a.toCharArray();
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            res = String.valueOf(chars);
        }
        return res;
    }

    /**
     * Check if every character in the given string appears only once.
     *
     * @param str the string to check
     * @return true if every character appears only once.
     */
    public static boolean isUnique(String str)
    {
        boolean ok = true;
        char[] chars = str.toCharArray();
        for (int i = 0; ok && i < chars.length; i++) {
            char ch = chars[i];
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[j] == ch) {
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }

    /**
     * Sum the digits of a string
     *
     * @param val A string containing only digits
     * @return the sum of the digits
     */
    public static int countDigits(String val)
    {
        int sum = 0;
        char[] digits = val.toCharArray();
        for (char digit : digits) {
            sum += (digit - ASCII_0);
        }
        return sum;
    }

    /**
     * Convert the given integer to a roman numeral.
     * 
     * @param n the integer to convert
     * @return the corresponding roman numeral
     * @throws IllegalArgumentException if n <= 0 or n > 10_000
     */
    public static String toRoman(int n)
    {
        if (n <= 0 || n > 10000) {
            throw new IllegalArgumentException("number must be included between 1 and 10_000: " + n);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROM_DIGITS.length; i++) {
            while (n >= ROM_DIGITS[i]) {
                sb.append(ROM_LETTERS[i]);
                n -= ROM_DIGITS[i];
            }
        }

        assert n == 0 : "Computation not done: n = " + n;
        return sb.toString();
    }
    
  	/**
  	 * @param a the first string
  	 * @param b the second string
  	 * @return true if the strings are permutations of each other (ex: "ABC" and "CBA")
  	 */
  	public static boolean isPermutation(String a, String b) {

  		if (a.length() != b.length()) {
  			return false;
  		}

  		char[] cha = a.toCharArray();
  		Arrays.sort(cha);
  		char[] chb = b.toCharArray();
  		Arrays.sort(chb);
  		return Arrays.equals(cha, chb);
  	}

}
