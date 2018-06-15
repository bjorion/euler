package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
 * <pre>
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 * </pre>
 * It is possible to make £2 in the following way:
 * <pre>
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 * </pre>
 * How many different ways can £2 be made using any number of coins?
 */
public class Euler031 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 200;
		long res; // 73682
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Brute Force (Simple) ", res, delta);

		Utils.start();
		res = calc2(max);
		delta = Utils.stop();
		Utils.print("BF with Recursion    ", res, delta);
	}

	/**
	 * Brute force using recursion. Works facter (not sure why).
	 */
	private static int calc2(int max) {

		int[] coins = { 200, 100, 50, 20, 10, 5, 2, 1 };
		int[] maxs = new int[coins.length];
		for (int i = 0; i < coins.length; i++) {
			maxs[i] = max / coins[i];
		}

		return calc2sub(coins, maxs, max, 0, 0, 0);
	}

	private static int calc2sub(final int[] coins, final int[] maxs, final int max, final int sum, final int index, final int count) {

		int count2 = count;
		for (int i = 0; i <= maxs[index]; i++) {
			int sumI = sum + i * coins[index];
			// goal reached: stop recursion here
			if (sumI == max) {
				count2++;
				break;
			}
			// above the goal: stop recursion here
			else if (sumI > max) {
				break;
			}
			// try next (smaller) coin
			else if (index + 1 < maxs.length) {
				count2 = calc2sub(coins, maxs, max, sumI, index + 1, count2);
			}
		}
		return count2;
	}

	/**
	 * Brute force.
	 */
	private static int calc1(int max) {

		int[] coins = { 200, 100, 50, 20, 10, 5, 2, 1 };
		int[] maxs = new int[coins.length];
		for (int i = 0; i < coins.length; i++) {
			maxs[i] = max / coins[i];
		}

		int count = 0;
		for (int a = 0; a <= maxs[0]; a++) {
			int sumA = a * coins[0];
			if (sumA > max) {
				break;
			}

			for (int b = 0; b <= maxs[1]; b++) {
				int sumB = sumA + b * coins[1];
				if (sumB > max) {
					break;
				}

				for (int c = 0; c <= maxs[2]; c++) {
					int sumC = sumB + c * coins[2];
					if (sumC > max) {
						break;
					}

					for (int d = 0; d <= maxs[3]; d++) {
						int sumD = sumC + d * coins[3];
						if (sumD > max) {
							break;
						}

						for (int e = 0; e <= maxs[4]; e++) {
							int sumE = sumD + e * coins[4];
							if (sumE > max) {
								break;
							}

							for (int f = 0; f <= maxs[5]; f++) {
								int sumF = sumE + f * coins[5];
								if (sumF > max) {
									break;
								}

								for (int g = 0; g <= maxs[6]; g++) {
									int sumG = sumF + g * coins[6];
									if (sumG > max) {
										break;
									}

									for (int h = 0; h <= maxs[7]; h++) {
										int sum = sumG + h * coins[7];
										if (sum == max) {
											count++;
										}
										if (sum > max) {
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return count;
	}

}