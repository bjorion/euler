package org.jorion.euler.util;

public class WordUtils {

	// --- Constants ---
	private static final int ASCII_0 = 48;

	// --- Methods ---
	protected static void wakeUp() {

	}

	/**
	 * @param val the number to reverse
	 * @return the reversed given number
	 */
	public static long reverse(long val) {

		String str = Long.toString(val);
		return Long.parseLong(reverse(str));
	}

	/**
	 * Reverse the given string.
	 *
	 * @return the string reversed
	 */
	public static String reverse(String str) {

		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * Test if the given number is palindromic (without converting this number to a String).
	 *
	 * @param val the number to test
	 * @return True if the given number is palindromic in base 10.
	 * @throws Exception if number length is greater than 20
	 */
	public static boolean isPalindromic(long val) {

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
	public static boolean isPalindromicStr(int val) {

		return isPalindromic(Integer.toString(val));
	}

	/**
	 * @return True if the given string is palindromic.
	 */
	public static boolean isPalindromic(String str) {

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
	public static String digitToString(int[] digits) {

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
	public static String swap(String a, int i, int j) {

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
	public static boolean isUnique(String str) {

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
	public static int countDigits(String val) {

		int sum = 0;
		char[] digits = val.toCharArray();
		for (char digit : digits) {
			sum += (digit - ASCII_0);
		}
		return sum;
	}
}
