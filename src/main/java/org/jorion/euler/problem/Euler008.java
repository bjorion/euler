package org.jorion.euler.problem;

import java.util.LinkedList;
import java.util.Queue;

import org.jorion.euler.util.Utils;

/**
 * The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.
 * <pre>
 * 73167176531330624919225119674426574742355349194934
 * 96983520312774506326239578318016984801869478851843
 * 85861560789112949495459501737958331952853208805511
 * 12540698747158523863050715693290963295227443043_557  -- 13 digits
 * 6689664895_0445244523161731856403098711121722383113
 * 62229893423380308135336276614282806444486645238749
 * 30358907296290491560440772390713810515859307960866
 * 70172427121883998797908792274921901699720888093776
 * 65727333001053367881220235421809751254540594752243
 * 52584907711670556013604839586446706324415722155397
 * 53697817977846174064955149290862569321978468622482
 * 83972241375657056057490261407972968652414535100474
 * 821663704844031_9989_0008895243450658541227588666881 -- 4 digits
 * 16427171479924442928230863465674813919123162824586
 * 17866458359124566529476545682848912883142607690042
 * 24219022671055626321111109370544217506941658960408
 * 07198403850962455444362981230987879927244284909188
 * 84580156166097919133875499200524063689912560717606
 * 05886116467109405077541002256983155200055935729725
 * 71636269561882670428252483600823257530420752963450
 * </pre>
 * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?
 */
public class Euler008 {

	// @formatter:off
	private static final String D = ""
			+ "73167176531330624919225119674426574742355349194934"
			+ "96983520312774506326239578318016984801869478851843"
			+ "85861560789112949495459501737958331952853208805511"
			+ "12540698747158523863050715693290963295227443043557"
			+ "66896648950445244523161731856403098711121722383113"
			+ "62229893423380308135336276614282806444486645238749"
			+ "30358907296290491560440772390713810515859307960866"
			+ "70172427121883998797908792274921901699720888093776"
			+ "65727333001053367881220235421809751254540594752243"
			+ "52584907711670556013604839586446706324415722155397"
			+ "53697817977846174064955149290862569321978468622482"
			+ "83972241375657056057490261407972968652414535100474"
			+ "82166370484403199890008895243450658541227588666881" // 9989
			+ "16427171479924442928230863465674813919123162824586"
			+ "17866458359124566529476545682848912883142607690042"
			+ "24219022671055626321111109370544217506941658960408"
			+ "07198403850962455444362981230987879927244284909188"
			+ "84580156166097919133875499200524063689912560717606"
			+ "05886116467109405077541002256983155200055935729725"
			+ "71636269561882670428252483600823257530420752963450";
	// @formatter:on

	private static final int LEN = D.length();

	private static final int ASCII_0 = 48;

	public static void main(String[] args) {

		final int max = 13;
		long res; // 23514624000
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Queue       ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("Queue 0     ", res, delta);

		Utils.start();
		res = calc3(max);
		delta = Utils.stop();
		Utils.print("Array (best)", res, delta);
	}

	/**
	 * Use a Queue.
	 */
	private static long calc1(int max) {

		long best = 1;
		Queue<Character> queue = new LinkedList<>();

		for (int i = 0; i < LEN; i++) {

			queue.add(D.charAt(i));
			if (queue.size() > max) {
				queue.remove();
			}
			if (queue.size() == max) {
				long prod = 1;
				for (Character elem : queue) {
					int val = (elem.hashCode() - ASCII_0);
					if (val == 0) {
						prod = 0;
						break;
					}
					prod *= val;
				}
				if (prod > best) {
					best = prod;
					// if (best == 23514624000L) {	System.out.println(queue); }
				}
			}
		}
		return best;
	}

	/**
	 * Use a Queue, ignore queue containing any zero.
	 */
	private static long calc2(int max) {

		final char zero = '0';
		long best = 1;
		Queue<Character> queue = new LinkedList<>();
		int zeros = 0;

		for (int i = 0; i < LEN; i++) {
			char c = D.charAt(i);
			if (c == zero) {
				zeros++;
			}
			queue.add(c);
			if (queue.size() > max) {
				if (queue.remove() == zero) {
					zeros--;
				}
			}
			if (zeros == 0 && queue.size() == max) {
				long prod = 1;
				for (Character elem : queue) {
					prod *= (elem.hashCode() - ASCII_0);
				}
				if (prod > best) {
					best = prod;
					// if (best == 23514624000L) {	System.out.println(queue); }
				}
			}
		}
		return best;
	}

	/**
	 * Use an array, ignore queue containing any zero.
	 */
	private static long calc3(int max) {

		int[] digits = new int[max];
		long best = 1;

		int zeros = max;
		int index = 0;
		for (int i = 0; i < LEN; i++) {
			int digit = D.charAt(i) - ASCII_0;
			if (digits[index] == 0) {
				zeros--;
			}
			if (digit == 0) {
				zeros++;
			}
			digits[index] = digit;
			index = (index + 1) % max;

			// if there is any zero, no need to compute the product
			if (zeros == 0) {
				long prod = 1;
				for (int elem : digits) {
					prod *= elem;
				}
				if (prod > best) {
					best = prod;
					// if (best == 23514624000L) {	System.out.println(list); }
				}
			}
		}
		return best;
	}
}
