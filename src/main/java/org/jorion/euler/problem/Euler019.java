package org.jorion.euler.problem;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.jorion.euler.util.Utils;

/**
 * You are given the following information, but you may prefer to do some research for yourself.
 * <pre>
 *     1 Jan 1900 was a Monday.
 *     Thirty days has September,
 *     April, June and November.
 *     All the rest have thirty-one,
 *     Saving February alone,
 *     Which has twenty-eight, rain or shine.
 *     And on leap years, twenty-nine.
 *     A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * </pre>
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
public class Euler019 {

	public static void main(String[] args) {

		long res;
		long delta;

		Utils.start();
		res = calc1();
		delta = Utils.stop();
		Utils.print("Use Java LocalDate    ", res, delta);

		Utils.start();
		res = calc2();
		delta = Utils.stop();
		Utils.print("Only arithmetic (Best)", res, delta);
	}

	/**
	 * Use Java date classes.
	 */
	private static long calc1() {

		long sum = 0;
		for (int y = 1901; y <= 2000; y++) {
			for (int m = 1; m <= 12; m++) {
				if (LocalDate.of(y, m, 1).getDayOfWeek() == DayOfWeek.SUNDAY) {
					sum++;
				}
			}
		}
		return sum;
	}

	/**
	 * Use arithmetic.
	 * Note: 01/01/1901 is tuesday
	 */
	private static long calc2() {

		final int[] dm = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		long sum = 0;
		int dow = 2; // DayOfWeek: 0=Sunday; 6=Saturday

		for (int y = 1901; y <= 2000; y++) {
			for (int m = 1; m <= 12; m++) {
				// we test for leap years
				int daysInMonth = (m == 2 && y % 4 == 0) ? 29 : dm[m - 1];
				dow = (dow + daysInMonth) % 7;
				if (dow == 0) {
					sum++;
				}
			}
		}

		return sum;
	}
}
