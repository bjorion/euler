package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	// --- Static ---
	private static final int LEN = 8;

	private static Set<String> _answers;

	// --- Methods ---
	public static void main(String[] args) throws Exception {

		String res;
		long delta;

		List<String> lines = Utils.readLines("p079_keylog.txt");
		Set<String> answers = lines.stream().collect(Collectors.toSet());
		_answers = answers;

		Utils.start();
		res = calc1();
		delta = Utils.stop();
		Utils.print("Tree Algorithm   ", res, delta);
	}

	/**
	 * We suppose here implicitly that each digit appears only once (8 different digits, so we check passcode at least 8 digits long).
	 */
	private static String calc1() {

		final int max = 10;
		boolean[] digits = new boolean[max];
		Element[] arr = new Element[max];

		for (int i = 0; i < max; i++) {
			arr[i] = new Element(i);
		}

		// check all the digits in the answers
		for (String answer : _answers) {
			int before = -1;
			for (int i = 0; i < answer.length(); i++) {
				int digit = answer.charAt(i) - '0';
				digits[digit] = true;
				if (i > 0) {
					Element current = arr[digit];
					arr[before].addElem(current);
				}
				before = digit;
			}
		}

		List<String> passcodes = new ArrayList<>();
		for (int i = 0; i < max; i++) {
			Element e = arr[i];
			e.next(passcodes, new StringBuilder());
		}

		return passcodes.get(0);
	}

	/**
	 * Return true if the given test string is valid.
	 *
	 * @param test the string to test
	 * @param answers the list of existing answers
	 * @return true if the given string matches all the answers
	 */
	@SuppressWarnings("unused")
	private static boolean isValid(String test) {

		final int len = 3;
		boolean valid = true;
		for (String answer : _answers) {
			int pos = 0;
			for (int i = 0; i < len; i++) {
				char ch = answer.charAt(i);
				pos = test.indexOf(ch, pos) + 1;
				if (pos <= 0) {
					valid = false;
					break;
				}
			}
			if (!valid) {
				break;
			}
		}
		return valid;
	}

	static class Element {

		final int digit;

		int count;

		private Set<Element> nextElems = new HashSet<>();

		// --- Methods ---
		Element(int digit) {

			this.digit = digit;
			this.count = 0;
		}

		void next(List<String> solutions, StringBuilder sb) {

			sb.append(Integer.toString(digit));
			count++;
			if (sb.length() < LEN) {
				for (Element nextElem : nextElems) {
					nextElem.next(solutions, sb);
				}
			} else {
				// System.out.println(sb.toString() + ": " + isValid(sb.toString()));
				solutions.add(sb.toString());
			}
			count--;
			sb.deleteCharAt(sb.length() - 1);
			return;
		}

		void addElem(Element next) {

			nextElems.add(next);
		}

		@Override
		public int hashCode() {

			return digit;
		}

		@Override
		public boolean equals(Object obj) {

			if (obj instanceof Element == false) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			Element other = (Element) obj;
			return this.digit == other.digit;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append("Element[digit: ").append(digit);
			sb.append(", count: ").append(count);
			sb.append(", next: ");
			for (Element e : nextElems) {
				sb.append(e.digit).append(" ");
			}
			return sb.toString();
		}

	}
}
