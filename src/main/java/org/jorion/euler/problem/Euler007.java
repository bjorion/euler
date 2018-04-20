package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

public class Euler007 {

	public static void main(String[] args) {

		final int max = 10_001;

		long res; // 100 => 25.164.150
		long start, delta;

		start = System.nanoTime();
		res = calc1(max);
		delta = System.nanoTime() - start;
		Utils.print("Simple", res, delta);
	}

	private static long calc1(int max) {

		long res = 0;
		long i = 3;
		int count = 1;
		while (count < max) {
			if (Utils.isPrime(i)) {
				res = i;
				count++;
			}
			i += 2;
		}
		return res;
	}

}
