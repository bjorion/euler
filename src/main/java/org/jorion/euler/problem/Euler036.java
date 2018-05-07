package org.jorion.euler.problem;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.jorion.euler.util.Utils;

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
		Utils.print("Simple    ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Stream    ", res, delta);

		// best result because of the previous invocation
		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Parallel  ", res, delta);
	}

	private static long calc1(int max) {

		int count = 0;
		long res = 0;
		for (int i = 1; i < max; i++) {
			if (Utils.isPalindromic(i)) {
				String bin = Integer.toBinaryString(i);
				if (Utils.isPalindromic(bin)) {
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

		OptionalInt sum = IntStream.range(1, max).filter(i -> Utils.isPalindromic(i)).filter(i -> {
			return Utils.isPalindromic(Integer.toBinaryString(i));
		}).reduce(Integer::sum);
		return sum.orElse(0);
	}

	private static long calc3(int max) {

		OptionalInt sum = IntStream.range(1, max).parallel().filter(i -> Utils.isPalindromic(i)).filter(i -> {
			return Utils.isPalindromic(Integer.toBinaryString(i));
		}).reduce(Integer::sum);
		return sum.orElse(0);
	}
}
