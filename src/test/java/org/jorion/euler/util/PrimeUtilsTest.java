package org.jorion.euler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for {@link PrimeUtils}.
 */
public class PrimeUtilsTest
{
    // --- Methods ---
    @Test
    public void testIsPrime()
    {

        assertFalse(PrimeUtils.isPrime(-1));
        assertFalse(PrimeUtils.isPrime(0));
        assertFalse(PrimeUtils.isPrime(1));
        assertTrue(PrimeUtils.isPrime(2));
        assertTrue(PrimeUtils.isPrime(3));
        assertFalse(PrimeUtils.isPrime(4));
        assertTrue(PrimeUtils.isPrime(5));
        assertFalse(PrimeUtils.isPrime(6));
        assertTrue(PrimeUtils.isPrime(7));
        assertFalse(PrimeUtils.isPrime(8));
        assertFalse(PrimeUtils.isPrime(9));
        assertFalse(PrimeUtils.isPrime(10));
    }

    @Test
    public void testIsPrime6()
    {

        assertFalse(PrimeUtils.isPrime6(-1));
        assertFalse(PrimeUtils.isPrime6(0));
        assertFalse(PrimeUtils.isPrime6(1));
        assertTrue(PrimeUtils.isPrime6(2));
        assertTrue(PrimeUtils.isPrime6(3));
        assertFalse(PrimeUtils.isPrime6(4));
        assertTrue(PrimeUtils.isPrime6(5));
        assertFalse(PrimeUtils.isPrime6(6));
        assertTrue(PrimeUtils.isPrime6(7));
        assertFalse(PrimeUtils.isPrime6(8));
        assertFalse(PrimeUtils.isPrime6(9));
        assertFalse(PrimeUtils.isPrime6(10));
    }

    @Test
    public void testIsPrimeSoE()
    {

        boolean[] res = PrimeUtils.isPrimeSoE(10);
        boolean[] primes = new boolean[] { false, false, true, true, false, true, false, true, false, false, false };
        assertEquals(primes.length, res.length);
        for (int i = 0; i < res.length; i++) {
            assertEquals(primes[i], res[i]);
        }
    }
}
