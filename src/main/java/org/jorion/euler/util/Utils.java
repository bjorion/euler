package org.jorion.euler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Set of utility methods.
 */
public class Utils {

	// --- Constants ---
	private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

	// --- Variables ---
	private static long start;

	// --- Methods ---
	/**
	 * Start the chronometer.
	 */
	public static void start() {
		MathUtils.wakeUp();
		PrimeUtils.wakeUp();
		start = System.nanoTime();
	}

	/**
	 * @return the chronometer value.
	 */
	public static long stop() {
		return System.nanoTime() - start;
	}

	/**
	 * Display a formatted string.
	 *
	 * @param msg a free text
	 * @param res a numerical result
	 * @param delta the computation time (in µs)
	 */
	public static void print(String msg, long res, long delta) {
		print(msg, Long.toString(res), delta);
	}

	public static void print(String msg, String res, long delta) {
		String nanosec = String.format("%8d ns", delta);
		String time = nanosec;
		delta /= 1000;
		if (delta >= 10) {
			String microsec = String.format("%8d µs", delta);
			time = microsec;
			delta /= 1000;
		}
		if (delta >= 10) {
			String millisec = String.format("%8d ms", delta);
			time = millisec;
		}
		// System.out.println(msg + " - Result: " + res + " - time: " + time);
		LOG.info(msg + " - Result: " + res + " - time: " + time);
	}

}
