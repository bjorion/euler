package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jorion.euler.util.Utils;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * <p>
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Euler005 {

	/**
	 * 2520 : 2 * 2 * 2 * 3 * 3 * 5 * 7 = 8 * 9 * 5 * 7
	 */
	public static void main(String[] args) {

		final int max = 20;
		int res; // 232.792.560
		long start, delta;

		start = System.nanoTime();
		res = calc1(max);
		delta = System.nanoTime() - start;
		Utils.print("Brute force (map) ", res, delta);

		start = System.nanoTime();
		res = calc2(max);
		delta = System.nanoTime() - start;
		Utils.print("Brute force (list)", res, delta);
	}

	/**
	 * Use a map to store the results.
	 */
	static int calc1(final int max) {

		Map<Integer, Integer> factors = new HashMap<>();

		for (int i = 2; i <= max; i++) {
			addMap(factors, findDivisorsAsMap(i));
		}
		// System.out.println(factors);

		int res = 1;
		for (Entry<Integer, Integer> entry : factors.entrySet()) {
			int key = entry.getKey().intValue();
			int val = entry.getValue().intValue();
			for (int i = 0; i < val; i++) {
				res *= key;
			}
		}
		return res;
	}

	/**
	 * Use a list to store the results.
	 */
	static int calc2(final int max) {

		List<Integer> factors = new ArrayList<>();
		for (int i = 2; i <= max; i++) {
			addList(factors, findDivisorsAsList(i));
		}
		// System.out.println(factors);
		int res = 1;
		for (int factor : factors) {
			res *= factor;
		}
		return res;
	}

	private static Map<Integer, Integer> findDivisorsAsMap(final int num) {

		int val = num;
		int i = 2;
		Map<Integer, Integer> map = new HashMap<>();

		while (val > 1) {
			if (val % i == 0) {
				Integer cnt = map.get(i);
				map.put(i, (cnt == null) ? 1 : cnt + 1);
				val /= i;
				continue;
			}
			i++;
		}
		return map;
	}

	private static List<Integer> findDivisorsAsList(final int num) {

		int val = num;
		int i = 2;
		List<Integer> list = new LinkedList<>();

		while (val > 1) {
			if (val % i == 0) {
				list.add(i);
				val /= i;
				continue;
			}
			i++;
		}
		return list;
	}

	private static void addMap(Map<Integer, Integer> factors, Map<Integer, Integer> map) {

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			int newValue = entry.getValue().intValue();
			Integer temp = factors.get(entry.getKey());
			int currValue = (temp == null) ? 0 : temp.intValue();
			if (newValue > currValue) {
				factors.put(entry.getKey(), newValue);
			}
		}
		return;
	}

	private static void addList(List<Integer> factors, List<Integer> list) {

		for (Integer i : factors) {
			int index = list.indexOf(i);
			if (index >= 0) {
				list.remove(index);
			}
		}
		factors.addAll(list);
		return;
	}
}
