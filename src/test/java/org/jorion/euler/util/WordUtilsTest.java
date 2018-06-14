package org.jorion.euler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for {@link WordUtils}.
 */
public class WordUtilsTest {

	// --- Methods ---
	@Test
	public void testReverse() {

		assertEquals(1, WordUtils.reverse(1));
		assertEquals(11, WordUtils.reverse(11));
		assertEquals(12345, WordUtils.reverse(54321));
		assertEquals(12345, WordUtils.reverse(543210));
	}

	@Test
	public void testIsPalindromicStr() {

		assertTrue(WordUtils.isPalindromicStr(1));
		assertTrue(WordUtils.isPalindromicStr(11));
		assertFalse(WordUtils.isPalindromicStr(12));
		assertTrue(WordUtils.isPalindromicStr(12321));
		assertFalse(WordUtils.isPalindromicStr(12331));
	}

	@Test
	public void testIsPalindromic2() {

		assertTrue(WordUtils.isPalindromic(1));
		assertTrue(WordUtils.isPalindromic(11));
		assertFalse(WordUtils.isPalindromic(12));
		assertTrue(WordUtils.isPalindromic(12321));
		assertFalse(WordUtils.isPalindromic(12331));
	}

	@Test
	public void testIsPalindromicFast() {

		final int len = 1_000_000;
		final int max = 100_000;

		// generate sample data
		int[] numbers = new int[len];
		for (int i = 0; i < len; i++) {
			numbers[i] = (int) Math.random() * max;
		}

		long delta;
		// test method 1
		Utils.start();
		for (int i = 0; i < len; i++) {
			WordUtils.isPalindromicStr(numbers[i]);
		}
		delta = Utils.stop();
		Utils.print("Method 1, #iter: " + len, "", delta);

		// test method 2
		Utils.start();
		for (int i = 0; i < len; i++) {
			WordUtils.isPalindromic(numbers[i]);
		}
		delta = Utils.stop();
		Utils.print("Method 2, #iter: " + len, "", delta);
	}

	@Test
	public void testArrayToString() {

		assertEquals("0", WordUtils.arrayToString(new int[0]));
		assertEquals("0", WordUtils.arrayToString(new int[10]));
		int[] digits = { 0, 1, 2, 3, 0, 0 };
		assertEquals("3210", WordUtils.arrayToString(digits));
	}

}
