package org.jorion.euler.problem;

import java.text.NumberFormat;
import java.util.Locale;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;

/**
 * Working from left-to-right if no digit is exceeded by the digit to its left it is called an increasing number; for example, 134468.
 * <p>
 * Similarly if no digit is exceeded by the digit to its right it is called a decreasing number; for example, 66420.
 * <p>
 * We shall call a positive integer that is neither increasing nor decreasing a "bouncy" number; for example, 155349.
 * <p>
 * As n increases, the proportion of bouncy numbers below n increases such that there are only 12_951 numbers below one-million that are not bouncy
 * and only 277_032 non-bouncy numbers below 10^10.
 * <p>
 * How many numbers below a googol (10^100) are not bouncy?
 */
public class Euler113 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 100;
		long res;
		long delta;

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();

		// Utils.print("'Next Step' algorithm", format(res), delta);
		Utils.print("Best algorithm       ", format(res), delta);
	}

	private static long calc2(int max) {

		return calcIncreasing(max) + calcDecreasing(max) - calcStable(max);
	}

	private static long calcStable(int max) {

		return max * 9;
	}

	private static long calcDecreasing(int max) {

		return max * 9;
	}

	private static long calcIncreasing(int max) {

		// Compute increasing numbers ('0' included)
		// 1 digit_ => 1 + 1 + ... + 1 = 10
		// 2 digits => 1.10 + 1.9 + 1.8 + ... + 1.1 = 55 = S(10)
		// 3 digits => 1.S(10) + 1.S(9) + _1.S(8) + ... + __1.S(1) = _220
		// 4 digits => 1.S(10) + 2.S(9) + _3.S(8) + ... + _10.S(1) = _715
		// 5 digits => 1.S(10) + 3.S(9) + _6.S(8) + ... + _55.S(1) = 2002
		// 6 digits => 1.S(10) + 4.S(9) + 10.S(8) + ... + 220.S(1) = 5005
		long[] c = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		for (int i = 4; i <= max; i++) {
			long[] d = new long[10];
			long acc = 0;
			for (int j = 0; j < 10; j++) {
				acc += c[j];
				d[j] = acc;
			}
			c = d;
		}

		long res = 0;
		for (int i = 0; i < 10; i++) {
			res += c[i] * MathUtils.sum(10 - i);
		}

		// System.out.println(Arrays.toString(c));
		return res - 1;
	}

	/**
	 * Not efficient enough algorithm. (10^50 = 20min)
	 */
	@SuppressWarnings("unused")
	private static long calc1(int max) {
		// zero included
		// 10^01: inc: __10, dec: _10, stable: _9+1
		// 10^02: inc: __55, dec: ___, stable: 18+1
		// 10^03: inc: _220, dec: ___, stable: 27+1
		// 10^04: inc: _715, dec: ___, stable: 36+1
		// 10^05: inc: 2002, dec: ___, stable: 45+1
		// 10^06: inc: 5005, dec: ___, stable: 54+1

		// zero not included
		// 10^10: inc: ________92.377, ________dec: _______184.745, stable: _90 = 90*1, sum: _______277.032 (___0.025s)
		// 10^20: inc: ____10.015.004 (100*~), dec: ____30.044.994, stable: 180 = 90*2, sum: ____40.059.818 (___0.400s = 16*~)
		// 10^30: inc: ___211.915.131 (_20*~), dec: ___847.660.497, stable: 270 = 90*3, sum: _1.059.575.358 (__10s = 25*~)
		// 10^40: inc: _2.054.455.633 (_10*~), dec: 10.272.278.129, stable: 360 = 90*4, sum: 12.326.733.402 (_125s = 12*~)
		// 10^50: inc: 12.565.671.260 (__6*~), dec: 75.394.027.515, stable: 450 = 90*5, sum: 87.959.698.325 (1137s = 9*~)

		long stable = 9 * max;
		System.out.println("stable: " + format(stable));

		long count1 = 0;
		BigInteger bi = new BigInteger(true, max);
		while (bi.next()) {
			count1++;
			System.out.println(bi.toString());
		}
		System.out.println("increasing: " + format(count1));

		long count2 = stable;
		// bi = new BigInteger(false, max);
		// while (bi.next()) {
		// count2++;
		// System.out.println(bi.toString());
		// }
		// count2--;
		// System.out.println("decreasing: " + format(count2));

		return (count1 + count2 - stable);
	}

	private static String format(long num) {

		return NumberFormat.getNumberInstance(Locale.GERMAN).format(num);
	}

	/**
	 * Represents an integer with many digits.
	 */
	static class BigInteger {

		static final int ASCII_0 = 48;

		final boolean inc;

		final int size;

		final char[] ch;

		// --- Methods ---
		BigInteger(boolean inc, int size) {

			this.inc = inc;
			this.size = size;
			this.ch = new char[size + 1];
			ch[0] = (inc) ? '0' : '1';
			for (int i = 1; i <= size; i++) {
				ch[i] = '0';
			}
		}

		@Override
		public String toString() {

			return new String(ch);
		}

		boolean next() {

			return inc ? nextInc() : nextDec();
		}

		/**
		 * Compute the next "increasing" number.
		 *
		 * @return false if we hit the ceiling
		 */
		boolean nextInc() {

			boolean ok = true;
			int curr = size;
			int start = curr;

			while (true) {
				int val = getVal(curr);
				if (val < 9) {
					int newVal = ++val;
					for (int index = curr; index <= start; index++) {
						setVal(index, newVal);
					}
					break;
				}
				int prev = curr - 1;
				if (prev <= 0) {
					ok = false;
					break;
				}
				curr = prev;
			}
			return ok;
		}

		/**
		 * Compute the next "decreasing" number.
		 *
		 * @return false if we hit the floor
		 */
		boolean nextDec() {

			boolean ok = true;
			int curr = size;
			int start = curr;

			while (true) {

				int val = getVal(curr);
				if (val > 0) {
					int newVal = --val;
					setVal(curr, newVal);
					break;
				}

				int prev = curr - 1;
				if (prev < 0) {
					ok = false;
					break;
				}

				int preVal = getVal(prev);
				if (preVal > 0) {
					int newVal = --preVal;
					setVal(prev, newVal);
					if (newVal == 0) {
						if (prev == 0 || getVal(prev - 1) == 0) {
							newVal = 9;
						}
					}
					for (int index = prev + 1; index <= start; index++) {
						setVal(index, newVal);
					}
					break;
				}

				curr = prev;
			}

			return ok;
		}

		/**
		 * Code is optimized to avoid looping through all leading zeros. We start from the last digits, but this is a little bit more complex.
		 *
		 * @deprecated not used anymore
		 * @return true if the number can be considered both as increasing AND decreasing
		 */
		@Deprecated
		boolean isStable() {

			// skip trailing zeros (if any)
			int index = size;
			while (index >= 0 && getVal(index) == 0) {
				index--;
			}
			if (index < size) {
				return (index == -1);
			}

			// check digits starting with the last one
			boolean done = false;
			boolean stable = true;
			index = size;
			int refVal = getVal(index);
			while (index > 0) {
				index--;
				int val = getVal(index);
				if (val == 0) {
					done = false;
					break;
				}
				if (val != refVal) {
					done = true;
					stable = false;
					break;
				}
			}

			// check all the remaining (leading) digits are zeros
			if (!done) {
				while (index > 0) {
					index--;
					if (getVal(index) != 0) {
						stable = false;
						break;
					}
				}
			}

			return stable;
		}

		int getVal(int index) {

			return (ch[index] - ASCII_0);
		}

		void setVal(int index, int val) {

			ch[index] = (char) (val + ASCII_0);
		}
	}

}