package org.jorion.euler.util;


import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link MathUtils}.
 */
public class MathUtilsTest {

    @Test
    public void testCPN() {
        assertEquals(1, MathUtils.cpn(0, 10));
        assertEquals(10, MathUtils.cpn(1, 10));
        assertEquals(252, MathUtils.cpn(5, 10));
        assertEquals(1, MathUtils.cpn(10, 10));
    }

    @Test
    public void testGCD() {
        assertEquals(1, MathUtils.gcd(2, 3));
        assertEquals(21, MathUtils.gcd(105, 252));
        assertEquals(21, MathUtils.gcd(252, 105));
    }

    @Test
    public void testGCDBI() {
        BigInteger a = new BigInteger("252");
        BigInteger b = new BigInteger("105");
        assertEquals(21, MathUtils.gcd(a, b).intValue());
    }

    @Test
    public void testLCM() {
        assertEquals(105L, MathUtils.lcm(15L, 21L));
        assertEquals(105L, MathUtils.lcm(21L, 15L));
    }

    @Test
    public void testSimplify() {
        int[] res = MathUtils.simplify(1, 100);
        assertEquals(1, res[0]);
        assertEquals(100, res[1]);

        res = MathUtils.simplify(3, 9);
        assertEquals(1, res[0]);
        assertEquals(3, res[1]);
    }

    @Test
    public void testSimplifyBI() {
        BigInteger a = new BigInteger("252");
        BigInteger b = new BigInteger("105");
        BigInteger[] res = MathUtils.simplify(a, b);
        assertEquals(12, res[0].intValue());
        assertEquals(5, res[1].intValue());
    }

    @Test
    public void testAddFraction() {
        long[] res = MathUtils.addFractions(new long[]{1, 2}, new long[]{3, 5});
        assertEquals(11, res[0]);
        assertEquals(10, res[1]);
    }

    @Test
    public void testAddFractionBI() {
        BigInteger i1 = new BigInteger("1");
        BigInteger i2 = new BigInteger("2");
        BigInteger i3 = new BigInteger("3");
        BigInteger i5 = new BigInteger("5");
        BigInteger[] res = MathUtils.addFractions(new BigInteger[]{i1, i2}, new BigInteger[]{i3, i5});
        assertEquals(11, res[0].intValue());
        assertEquals(10, res[1].intValue());
    }

    @Test
    public void testIsPandigital() {
        assertTrue(MathUtils.isPandigital(1));
        assertTrue(MathUtils.isPandigital(123456789));
        assertTrue(MathUtils.isPandigital(987654321));
        // "0" not allowed
        assertFalse(MathUtils.isPandigital(1230456));
        // missing "4"
        assertFalse(MathUtils.isPandigital(123567));
    }
}
