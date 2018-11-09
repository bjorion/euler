package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * By counting carefully it can be seen that a rectangular grid measuring 3 by 2 contains eighteen rectangles:
 *
 * <pre>
 * 1x1 => 6
 * 1x2 => 4
 * 1x3 => 2
 * 2x1 => 3
 * 2x2 => 2
 * 2x3 => 1
 * </pre>
 *
 * Although there exists no rectangular grid that contains exactly two million rectangles, find the area of the grid with the nearest solution.
 */
public class Euler085 {

	// --- Methods ---
	public static void main(String[] args) {

		final int goal = 2_000_000;
		long res; // 77 * 36 => 1999998 (delta = 2)
		long delta;

		Utils.start();
		res = calc1(goal);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);

		System.out.println("77x36: " + Math.abs(calcRect(77, 36))); // 5146
	}

	private static long calc1(int goal) {

		int bestDelta = goal;
		int bestX = 0;
		int bestY = 0;

		// increase Y
		int y = 11;
		while (y < 200) {

			int x = 10;
			int bestXtmp = 0;
			int bestDeltaX = goal;

			// increase X
			while (true) {
				int count = calcRect(x, y);
				int delta = Math.abs(goal - count);
				if (delta < bestDeltaX) {
					bestDeltaX = delta;
					bestXtmp = x;
					x++;
				}
				else {
					// System.out.println("bestDeltaX: " + bestDeltaX + " for X: " + bestXtmp + ", y: " + y);
					break;
				}
			}

			if (bestDeltaX < bestDelta) {
				bestDelta = bestDeltaX;
				bestY = y;
				bestX = bestXtmp;
			}
			y++;
		}
		// System.out.println("x: " + bestX + ", y: " + bestY + ", bestDelta: " + bestDelta);
		return bestX * bestY;
	}

	/**
	 * Calculate the number of inner rectangles for a given rectangle.
	 */
	private static int calcRect(int w, int h) {

		int res = 0;
		for (int y = 1; y <= h; y++) {
			for (int x = 1; x <= w; x++) {
				int valx = w - x + 1;
				int valy = h - y + 1;
				// System.out.println(String.format("%s * %s = %s", x, y, valx * valy));
				res += valx * valy;
			}
		}

		return res;
	}

}