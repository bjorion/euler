package org.jorion.euler.problem;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jorion.euler.util.Utils;

/**
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
 * For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * <p>
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.
 * <p>
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24.
 * By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers.
 * However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number
 * that cannot be expressed as the sum of two abundant numbers is less than this limit.
 * <p>
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public class Euler023 {

	// --- Constants ---

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 28123;
		long res; // 4_179_871
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("My Algorithm      ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("My Algorithm (alt)", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Streams (10x)     ", res, delta);
	}

	/**
	 * Find all abundant numbers.
	 * <p>
	 * Then loop through all the numbers from 1 to max, and for each, try to find two abundant numbers whose sum is the current number.
	 */
	private static long calc1(int max) {

		int smallestAbundant = 12;

		// find all abundant numbers
		boolean[] numbers = new boolean[max + 1];
		for (int i = 2; i <= max; i++) {
			if (computeSumDivisor(i) > i) {
				numbers[i] = true;
			}
		}

		int sum = 0;
		for (int i = 1; i <= max; i++) {
			// if number "i" the sum of two abundant numbers?
			boolean found = false;
			for (int j = smallestAbundant; j <= (i + 1) / 2; j++) {
				if (numbers[j] && numbers[i - j]) {
					// System.out.println("number: " + i + ", sum of: " + j + " and: " + (i - j));
					found = true;
					break;
				}
			}
			if (!found) {
				// System.out.println("adding: " + i);
				sum += i;
			}
		}
		return sum;
	}

	/**
	 * Find all abundant numbers.
	 * <p>
	 * Then computer all possible sums, and finally loop through all integers, removing anyone that's a sum of abundant numbers.
	 */
	private static long calc2(int max) {

		int smallestAbundant = 12;

		// find all abundant numbers
		boolean[] numbers = new boolean[max + 1];
		for (int i = 2; i <= max; i++) {
			if (computeSumDivisor(i) > i) {
				numbers[i] = true;
			}
		}

		boolean[] sums = new boolean[max + 1];
		for (int i = smallestAbundant; i <= max / 2; i++) {
			for (int j = smallestAbundant; j <= max; j++) {
				if (i + j > max) {
					break;
				}
				if (numbers[i] && numbers[j]) {
					sums[i + j] = true;
				}
			}
		}

		int sum = 0;
		for (int i = 1; i <= max; i++) {
			if (!sums[i]) {
				sum += i;
			}
		}
		return sum;
	}

	/**
	* https://projecteuler.net/thread=23;page=8
	*/
	public static int calc3(int max) {

		int result = 0;

		// @formatter:off
    Set<Integer> iset = calcSumsOfTwoAbundants(
    			// loop through all the numbers
    			IntStream.range(1, max)
    			// keep only the "abundant" ones
          .filter(i -> computeSumDivisor(i) > i), max
    );

    // loop through all numbers and keep only those present in the set, then make the sum
    result = IntStream.range(1, max)
            .filter(x -> !iset.contains(x))
            .sum();
    // @formatter:on

		return result;
	}

	/**
	 * @param abundants a stream of abundants numbers
	 */
	private static Set<Integer> calcSumsOfTwoAbundants(IntStream abundants, int max) {

		int[] arr = abundants.toArray();

		// @formatter:off
		Set<Integer> result =
				IntStream.range(0, arr.length - 2)
				.flatMap(
						i -> IntStream.range(i, arr.length - 1)
						.map(j -> arr[i] + arr[j])
						.filter(s -> s <= max))
				.boxed()
				.collect(Collectors.toSet());
		// @formatter:on

		return result;
	}

	/**
	 * @return the sum of all proper divisors of the given number
	 */
	private static int computeSumDivisor(int val) {

		int sum = 1;
		int sqrt = (int) Math.sqrt(val);
		for (int n = 2; n <= sqrt; n++) {
			if (val % n == 0) {
				sum += n;
				int m = val / n;
				if (m != n) {
					sum += m;
				}
			}
		}
		return sum;
	}

}