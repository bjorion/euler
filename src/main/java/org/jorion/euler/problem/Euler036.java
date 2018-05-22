package org.jorion.euler.problem;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.
 * <p>
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 * <p>
 * (Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class Euler036 {

	// --- Methods ---
	public static void main(String[] args) throws Exception {

		int max = 1_000_000;
		long res; // 872187 (19 numbers)
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple     ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Stream     ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Stream 2x  ", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Parallel   ", res, delta);

		Utils.start();
		res = calc4(max);
		delta = Utils.stop();
		Utils.print("Algo (best)", res, delta);
	}

	private static long calc1(int max) {

		int count = 0;
		long res = 0;
		// even number are no palindrom in binary
		for (int i = 1; i < max; i = i + 2) {
			if (WordUtils.isPalindromic(i)) {
				String bin = Integer.toBinaryString(i);
				if (WordUtils.isPalindromic(bin)) {
					// System.out.println("i: " + i + ", " + bin);
					res += i;
					count++;
				}
			}
		}
		System.out.println(count);
		return res;
	}

	private static long calc2(int max) {

		OptionalInt sum = IntStream.range(1, max).filter(i -> i % 2 == 1).filter(i -> WordUtils.isPalindromic(i)).filter(i -> {
			return WordUtils.isPalindromic(Integer.toBinaryString(i));
		}).reduce(Integer::sum);
		return sum.orElse(0);
	}

	private static long calc3(int max) {

		OptionalInt sum = IntStream.range(1, max).parallel().filter(i -> i % 2 == 1).filter(i -> WordUtils.isPalindromic(i)).filter(i -> {
			return WordUtils.isPalindromic(Integer.toBinaryString(i));
		}).reduce(Integer::sum);
		return sum.orElse(0);
	}

	/**
	 * The only 1-digit and 2-digit palindromic numbers in both base 10 and base 2 are: 1, 3, 5, 7, 9, 33, 99 add up = 157
	 * <p>
	 * let 3-digit base 10 palindromic number = aba = 100a + 10b + a = 101a + 10b<br/>
	 * where a = 1,2,...,8,9 ; b = 0,1,...,8,9
	 * <p>
	 * Using the same method as above and find for 4-digit, 5-digit and 6-digit
	 * <p>
	 * <ul>
	 * <li>4-digit base 10 palindromic number = 11 (91a + 10b)
	 * <li>5-digit base 10 palindromic number = 10001a + 1010b + 100c
	 * <li>6-digit base 10 palindromic number = 11 (9091a + 910b + 100c)
	 * </ul>
	 */
	private static long calc4(int max) {

		long res = 157;
		for (int n = 3; n <= 6; n++) {
			for (int a = 1; a <= 9; a++) {
				for (int b = 0; b <= 9; b++) {
					for (int c = 0; c <= 9; c++) {
						int val = 0;
						if (n == 3) {
							val = 101 * a + 10 * b;
						}
						if (n == 4) {
							val = 11 * (91 * a + 10 * b);
						}
						if (n == 5) {
							val = 10001 * a + 1010 * b + 100 * c;
						}
						if (n == 6) {
							val = 11 * (9091 * a + 910 * b + 100 * c);
						}
						if (WordUtils.isPalindromic(Integer.toBinaryString(val))) {
							res += val;
						}
						if (n < 5) {
							break;
						}
					}
				}
			}
		}
		return res;
	}
}
