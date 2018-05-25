package org.jorion.euler.util;

public class WordUtils {

	// --- Methods ---
	/**
	 * @return True if the given number is palindromic in base 10.
	 */
	public static boolean isPalindromic(int val) {
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
	public static String arrayToString(int[] digits) {

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

}
