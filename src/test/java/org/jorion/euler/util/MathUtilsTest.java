package org.jorion.euler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void testSimplify()
    {
        int[] res = MathUtils.simplify(1, 100);
        assertEquals(1, res[0]);
        assertEquals(100, res[1]);

        res = MathUtils.simplify(3, 9);
        assertEquals(1, res[0]);
        assertEquals(3, res[1]);
    }
    
    @Test
    public void testIsPandigital()
    {
        assertTrue(MathUtils.isPandigital(1));
        assertTrue(MathUtils.isPandigital(123456789));
        assertTrue(MathUtils.isPandigital(987654321));
        // "0" not allowed
        assertFalse(MathUtils.isPandigital(1230456));
        // missing "4"
        assertFalse(MathUtils.isPandigital(123567));
    }
}
