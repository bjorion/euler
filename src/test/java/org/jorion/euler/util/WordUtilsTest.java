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
	public void testIsPalindromic() {
		assertTrue(WordUtils.isPalindromic(1));
		assertTrue(WordUtils.isPalindromic(11));
		assertFalse(WordUtils.isPalindromic(12));
	}

	@Test
	public void testArrayToString() {
		assertEquals("0", WordUtils.arrayToString(new int[0]));
		assertEquals("0", WordUtils.arrayToString(new int[10]));
		int[] digits = { 0, 1, 2, 3, 0, 0 };
		assertEquals("3210", WordUtils.arrayToString(digits));
	}
}
