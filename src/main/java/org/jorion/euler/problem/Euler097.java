package org.jorion.euler.problem;

import java.math.BigInteger;

import org.jorion.euler.util.Utils;

/**
 * The first known prime found to exceed one million digits was discovered in 1999, and is a Mersenne prime of the form
 * 2^6972593−1; it contains exactly 2,098,960 digits. Subsequently other Mersenne primes, of the form 2^p−1, have been
 * found which contain more digits.
 * <p>
 * However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits: 28433×2^7830457+1.
 * <p>
 * Find the last ten digits of this prime number.
 */
public class Euler097
{
    // --- Constants ---
    private static final int POW = 7830457;
    
    private static final int MUL = 28433;
    
    // --- Methods ---
    public static void main(String[] args)
    {
        String res; // 8739992577
        long delta;

        Utils.start();
        res = calc1(10);
        delta = Utils.stop();
        Utils.print("Brute force ", res, delta);
    }

    private static String calc1(int size)
    {
        BigInteger bi = new BigInteger("2");
        bi = bi.pow(POW);
        String res = bi.toString();
        res = res.substring(res.length() - size, res.length());
        bi = new BigInteger(res).multiply(new BigInteger(Integer.toString(MUL))).add(BigInteger.ONE);
        res = bi.toString();
        return res.substring(res.length() - size, res.length());
    }

}