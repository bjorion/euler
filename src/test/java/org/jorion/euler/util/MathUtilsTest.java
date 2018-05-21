package org.jorion.euler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

/**
 * Unit test for {@link MathUtils}.
 */
public class MathUtilsTest
{
    // --- Methods ---
    @Test
    public void testCPN()
    {
        assertEquals(1, MathUtils.cpn(0, 10));
        assertEquals(10, MathUtils.cpn(1, 10));
        assertEquals(252, MathUtils.cpn(5, 10));
        assertEquals(1, MathUtils.cpn(10, 10));
    }

    @Test
    public void testGCD()
    {
        assertEquals(1, MathUtils.gcd(2, 3));
        assertEquals(21, MathUtils.gcd(105, 252));
        assertEquals(21, MathUtils.gcd(252, 105));
    }

    @Test
    public void testFindPrimeFactors()
    {
        // {2=2, 3=1, 5=1}
        Map<Integer, Integer> map = MathUtils.findPrimeFactors(60);
        assertTrue(map.get(2).equals(2));
        assertTrue(map.get(3).equals(1));
        assertTrue(map.get(5).equals(1));
        assertFalse(map.containsKey(1));

        map = MathUtils.findPrimeFactors(97);
        assertTrue(map.get(97).equals(1));
    }
}
