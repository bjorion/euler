package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * A common security method used for online banking is to ask the user for three random characters from a passcode.
 * For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.
 * <p>
 * The text file, keylog.txt, contains fifty successful login attempts.
 * <p>
 * Given that the three characters are always asked for in order,
 * analyse the file so as to determine the shortest possible secret passcode of unknown length.
 */
public class Euler079 {

	// --- Methods ---
	public static void main(String[] args) {

		final int max = 20;
		long res;
		long delta;

		Utils.start();
		res = calc1(max);
		delta = Utils.stop();
		Utils.print("Simple ", res, delta);
	}

	private static long calc1(int max) {

		return 0;
	}

}