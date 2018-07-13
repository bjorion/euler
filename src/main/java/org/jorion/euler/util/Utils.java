package org.jorion.euler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

		// make sure the classes below are loaded by the JVM
		// (we don't want the loading time to be included in the chronometer)
		MathUtils.wakeUp();
		PrimeUtils.wakeUp();
		WordUtils.wakeUp();

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

	/**
	 * Read a single-line file.
	 *
	 * @param filename the filename
	 * @return the line
	 */
	public static String readLine(String filename)
			throws Exception {

		String line = null;
		URL url = Utils.class.getClassLoader().getResource(filename);
		File file = new File(url.getFile());

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			line = br.readLine();
		}
		return line;
	}

	/**
	 * Read a multiple-line file.
	 *
	 * @param filename the filename
	 * @return a list of lines
	 */
	public static List<String> readLines(String filename)
			throws Exception {

		List<String> lines = new ArrayList<>();
		URL url = Utils.class.getClassLoader().getResource(filename);
		Path path = Paths.get(url.toURI());

		// br returns a stream of lines; convert it to a List
		try (BufferedReader br = Files.newBufferedReader(path)) {
			lines = br.lines().collect(Collectors.toList());
		}
		return lines;
	}

	/**
	 * Given an array of booleans, returns the index of the next "true" in the array.
	 *
	 * @param arr an array of booleans
	 * @param index the current index
	 * @return the index of the next true (greater than the given index), or -1 if it does not exist
	 */
	public static int nextTrue(boolean[] arr, int index) {

		int len = arr.length;
		while (index < len - 1) {
			index++;
			if (arr[index]) {
				break;
			}
		}
		return (index < len - 1) ? index : -1;
	}
}
