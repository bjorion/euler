package org.jorion.euler.problem;

import java.util.HashMap;
import java.util.Map;

import org.jorion.euler.util.Utils;

/**
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 * <p>
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 * <p>
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 * <pre>
 *  3! = 6
 *  4! = 24
 * 	5! = 120
 *  6! = 720
 *  7! = 5040
 *  8! = 40320
 *  9! = 362880
 * </pre>
 */
public class Euler034 {

	// --- Constants ---
	private static final int FACT[] = new int[10];

	private static final int ASCII_0 = 48;

	static {
		FACT[0] = 1;
		for (int i = 1; i <= 9; i++) {
			FACT[i] = FACT[i - 1] * i;
		}
	}

	// --- Methods ---
	public static void main(String[] args) throws Exception {

		long res; // 145 + 40585 = 40730
		long delta;

		Utils.start();
		res = calc1();
		delta = Utils.stop();
		Utils.print("Simple   ", res, delta);

		Utils.start();
		res = calc2();
		delta = Utils.stop();
		Utils.print("Advanced ", res, delta);
	}

	/**
	 * Test all numbers between 10 and 10 000 000.
	 */
	public static long calc1() {

		long res = 0;
		int min = 10;
		int max = 10_000_000;

		for (int i = min; i < max; i++) {
			String str = Integer.toString(i);
			long sum = 0;
			for (int j = 0; j < str.length(); j++) {
				int digit = str.charAt(j) - ASCII_0;
				sum += FACT[digit];
			}
			if (sum == i) {
				// System.out.println("Found: " + i);
				res += sum;
			}
		}
		return res;
	}

	/**
	 * Test all numbers between 10 and 10 000 000 except those containing a digit "too high".
	 */
	private static long calc2() {

		Map<Integer, Integer> map = new HashMap<>();
		map.put(10, 5); // 5! > 100
		map.put(100, 7); // 7! > 1000
		map.put(1_000, 8); // 8! > 10000
		map.put(10_000, 9); // 9! > 100000

		long res = 0;
		int max = 10_000_000;

		for (int min = 10; min < max; min *= 10) {
			int maxDigit = map.containsKey(min) ? map.get(min) : -1;

			for (int i = min; i < min * 10; i++) {
				String str = Integer.toString(i);

				// make sure every digit is "not too high"
				if (maxDigit > 0) {
					boolean flag = false;
					for (int j = 0; j < str.length(); j++) {
						int digit = str.charAt(j) - ASCII_0;
						if (digit > maxDigit) {
							flag = true;
							break;
						}
					}
					if (flag) {
						continue;
					}
				}

				long sum = 0;
				for (int j = 0; j < str.length(); j++) {
					int digit = str.charAt(j) - ASCII_0;
					sum += FACT[digit];
				}
				if (sum == i) {
					// System.out.println("Found: " + i);
					res += sum;
				}
			}
		}
		return res;
	}

}
