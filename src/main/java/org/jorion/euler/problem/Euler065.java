package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.MathUtils;
import org.jorion.euler.util.Utils;
import org.jorion.euler.util.WordUtils;

/**
 * <p>
 * The square root of 2 can be written as an infinite continued fraction.
 * </p>
 * <div style="margin-left:20px;">
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tr>
 * <td>√2 = 1 +</td>
 * <td colspan="4" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td>2 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>2 + ...</td>
 * </tr>
 * </table>
 * </div>
 * <p>
 * The infinite continued fraction can be written, √2 = [1;(2)], (2) indicates that 2 repeats <i>ad infinitum</i>. In a similar way, √23 =
 * [4;(1,3,1,8)]. ( = 4 + 1 / (1 + 1 / (3 + 1 / (1 + 1 / (8 + 1 / (1 + ...))))))
 * </p>
 * <p>
 * It turns out that the sequence of partial values of continued fractions for square roots provide the best rational approximations. Let us consider
 * the convergents for √2.
 * </p>
 * <div style="margin-left:20px;">
 *
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tr>
 * <td>1 +</td>
 * <td style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * <td>= 3/2</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td><div style="text-align:center;">2</div></td>
 * <td></td>
 * </tr>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tr>
 * <td>1 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * <td>= 7/5</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td><div style="text-align:center;">2</div></td>
 * <td></td>
 * </tr>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tr>
 * <td>1 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * <td>= 17/12</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * <td></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * <td></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td><div style="text-align:center;">2</div></td>
 * <td></td>
 * </tr>
 * </table>
 * <table border="0" cellspacing="0" cellpadding="0">
 * <tr>
 * <td>1 +</td>
 * <td colspan="4" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * <td>= 41/29</td>
 * </tr>
 * <tr>
 * <td></td>
 * <td>2 +</td>
 * <td colspan="3" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td>2 +</td>
 * <td colspan="2" style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * <td></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>2 +</td>
 * <td style="border-bottom:1px solid #000;"><div style="text-align:center;">1</div></td>
 * <td></td>
 * </tr>
 * <tr>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td><div style="text-align:center;">2</div></td>
 * <td></td>
 * </tr>
 * </table>
 * </div>
 * <p>
 * Hence the sequence of the first ten convergents for √2 are:
 * </p>
 * <div class="note">1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...</div>
 * <p>
 * What is most surprising is that the important mathematical constant,<br />
 * <i>e</i> = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2<i>k</i>,1, ...].
 * </p>
 * <p>
 * The first ten terms in the sequence of convergents for <i>e</i> are:
 * </p>
 * <div class="note">2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...</div>
 * <p>
 * The sum of digits in the numerator of the 10<sup>th</sup> convergent is 1+4+5+7=17.
 * </p>
 * <p>
 * Find the sum of digits in the numerator of the 100<sup>th</sup> convergent of the continued fraction for <i>e</i>.
 * </p>
 */
public class Euler065 {

	// --- Methods ---
    // e = 2.71828 18284 59045 23536 02874 71352 66249 77572 47093 69995...
	public static void main(String[] args) {

		final int max = 100;
		int res; // 272
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Loop      ", res, delta);
	}

	private static int calc1(int max) {

		max--;
		BigInteger[] res = { BigInteger.ZERO, BigInteger.ONE };
		for (int i = max; i > 0; i--) {

		    // e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...]
			int val = (i % 3 == 2) ? ((i + 1) / 3) * 2 : 1;
			// System.out.println("i: " + i + ", val: " + val);

			// compute (val + res)
			BigInteger[] tmp = MathUtils.addFractions(new BigInteger[] { new BigInteger(Integer.toString(val)), BigInteger.ONE }, res);
			
			// compute res = 1 / tmp
			res[0] = tmp[1];
			res[1] = tmp[0];
		}
		// add starting 2
		res = MathUtils.addFractions(new BigInteger[] { MathUtils.TWO, BigInteger.ONE }, res);
		// System.out.println(res[0].toString() + "/" + res[1].toString());
		return WordUtils.countDigits(res[0].toString());
	}

	@SuppressWarnings("unused")
	private static float fraction(int start, int[] denoms, int max) {

		int len = denoms.length;
		max = (max / len) + 1;
		float res = 0;

		for (int i = 0; i < max; i++) {
			for (int j = 1; j <= len; j++) {
				res = 1 / (denoms[len - j] + res);
			}
		}
		return start + res;
	}

}