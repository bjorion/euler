package org.jorion.euler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UtilsTest {

	// --- Methods ---
	@Test
	public void testIsPrime() {

		assertFalse(Utils.isPrime(-1));
		assertFalse(Utils.isPrime(0));
		assertFalse(Utils.isPrime(1));
		assertTrue(Utils.isPrime(2));
		assertTrue(Utils.isPrime(3));
		assertFalse(Utils.isPrime(4));
		assertTrue(Utils.isPrime(5));
		assertFalse(Utils.isPrime(6));
		assertTrue(Utils.isPrime(7));
		assertFalse(Utils.isPrime(8));
		assertFalse(Utils.isPrime(9));
		assertFalse(Utils.isPrime(10));
	}

	@Test
	public void testIsPrime6() {

		assertFalse(Utils.isPrime6(-1));
		assertFalse(Utils.isPrime6(0));
		assertFalse(Utils.isPrime6(1));
		assertTrue(Utils.isPrime6(2));
		assertTrue(Utils.isPrime6(3));
		assertFalse(Utils.isPrime6(4));
		assertTrue(Utils.isPrime6(5));
		assertFalse(Utils.isPrime6(6));
		assertTrue(Utils.isPrime6(7));
		assertFalse(Utils.isPrime6(8));
		assertFalse(Utils.isPrime6(9));
		assertFalse(Utils.isPrime6(10));
	}

	@Test
	public void testIsPrimeSoE() {

		boolean[] res = Utils.isPrimeSoE(10);
		boolean[] primes = new boolean[] { false, false, true, true, false, true, false, true, false, false, false };
		assertEquals(primes.length, res.length);
		for (int i = 0; i < res.length; i++) {
			assertEquals(primes[i], res[i]);
		}
	}

	@Test
	public void testIsPalindromic() {

		assertTrue(Utils.isPalindromic(1));
		assertTrue(Utils.isPalindromic(11));
		assertFalse(Utils.isPalindromic(12));
	}

}
