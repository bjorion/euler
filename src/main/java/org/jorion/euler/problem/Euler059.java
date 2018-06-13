package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code for Information Interchange).
 * For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 * <p>
 * A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken from a secret key.
 * The advantage with the XOR function is that using the same encryption key on the cipher text, restores the plain text;
 * for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
 * <p>
 * For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random bytes.
 * The user would keep the encrypted message and the encryption key in different locations, and without both "halves", it is impossible to decrypt the message.
 * <p>
 * Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key.
 * If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message.
 * The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.
 * <p>
 * Your task has been made easy, as the encryption key consists of three lower case characters.
 * Using cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes,
 * and the knowledge that the plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the original text.
 */
public class Euler059 {

	// --- Constants ---
	private static final int sp = 32;
	private static final int a = 97; // 0x61
	private static final int z = a + 25;

	// --- Methods ---
	public static void main(String[] args) throws Exception {

		long res; // 107359
		long delta;

		String line = Utils.readLine("p059_cipher.txt");
		// line = "35,4,19,15,0,19,5";

		Utils.start();
		res = calc1(line);
		delta = Utils.stop();
		Utils.print("Simple ", res, delta);
	}

	private static long calc1(String line) {

		String[] chrs = line.split(",");
		int len = chrs.length;
		int[] encrypted = new int[len];
		int[] uncrypted = new int[len];

		for (int i = 0; i < chrs.length; i++) {
			encrypted[i] = Integer.parseInt(chrs[i]);
		}

		int sum = 0;
		// encryption key = 3 lowercase characters
		for (int i = a; i <= z; i++) {
			for (int j = a; j <= z; j++) {
				for (int k = a; k <= z; k++) {
					boolean ok = true;
					int n = 0;

					for (n = 0; n < len;) {
						int dst = uncrypt(encrypted[n], i);
						if (dst < 0) {
							ok = false;
							break;
						}
						uncrypted[n] = dst;
						n++;
						if (n >= len) {
							continue;
						}

						dst = uncrypt(encrypted[n], j);
						if (dst < 0) {
							ok = false;
							break;
						}
						uncrypted[n] = dst;
						n++;
						if (n >= len) {
							continue;
						}

						dst = uncrypt(encrypted[n], k);
						if (dst < 0) {
							ok = false;
							break;
						}
						uncrypted[n] = dst;
						n++;
						if (n >= len) {
							continue;
						}
					}
					if (ok) {
						sum = 0;
						for (n = 0; n < len; n++) {
							sum += uncrypted[n];
						}
						// System.out.println(toString(uncrypted));
						// System.out.println(toString(new int[] { i, j, k }));
					}
				}
			}
		}
		return sum;
	}

	private static int uncrypt(int src, int key) {

		int dst = src ^ key;
		if (sp <= dst && dst <= z && dst != 35 && dst != 96) {
			// ok
		} else {
			dst = -1;
		}
		return dst;
	}

	@SuppressWarnings("unused")
	private static String toString(int[] chrs) {

		StringBuilder sb = new StringBuilder();
		for (int chr : chrs) {
			sb.append(Character.toString((char) chr));
		}
		return sb.toString();
	}
}