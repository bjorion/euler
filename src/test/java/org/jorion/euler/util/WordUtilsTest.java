package org.jorion.euler.util;

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
}
