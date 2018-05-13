package org.jorion.euler.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for {@link Utils}.
 */
public class UtilsTest
{
    // --- Methods ---
    @Test
    public void testIsPalindromic()
    {

        assertTrue(Utils.isPalindromic(1));
        assertTrue(Utils.isPalindromic(11));
        assertFalse(Utils.isPalindromic(12));
    }

}
