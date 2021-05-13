package org.jorion.euler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

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

    @Test
    public void testFindPrimeFactors()
    {
        // {2=2, 3=1, 5=1}
        Map<Integer, Integer> map = PrimeUtils.findPrimeFactors(60);
        assertTrue(map.get(2).equals(2));
        assertTrue(map.get(3).equals(1));
        assertTrue(map.get(5).equals(1));
        assertFalse(map.containsKey(1));

        map = PrimeUtils.findPrimeFactors(97);
        assertTrue(map.get(97).equals(1));
    }

    @Test
    public void testPhi()
    {
        boolean[] primes = PrimeUtils.isPrimeSoE(100_000);
        assertEquals(79180, PrimeUtils.phi(87109, primes));
        assertEquals(7918, PrimeUtils.phi(7919, primes));
    }

}
