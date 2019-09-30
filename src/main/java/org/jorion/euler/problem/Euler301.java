package org.jorion.euler.problem;

import org.jorion.euler.util.Utils;

/**
 * Nim is a game played with heaps of stones, where two players take it in turn to remove any number of stones from any
 * heap until no stones remain.
 *
 * We'll consider the three-heap normal-play version of Nim, which works as follows:
 * <ul>
 * <li>At the start of the game there are three heaps of stones.
 * <li>On his turn the player removes any positive number of stones from any single heap.
 * <li>The first player unable to move (because no stones remain) loses.
 * </ul>
 *
 * If (n1,n2,n3) indicates a Nim position consisting of heaps of size n1, n2 and n3 then there is a simple function
 * X(n1,n2,n3) — that you may look up or attempt to deduce for yourself — that returns:
 * <ul>
 * <li>zero if, with perfect strategy, the player about to move will eventually lose; or
 * <li>non-zero if, with perfect strategy, the player about to move will eventually win.
 * </ul>
 *
 * For example X(1,2,3) = 0 because, no matter what the current player does, his opponent can respond with a move that
 * leaves two heaps of equal size, at which point every move by the current player can be mirrored by his opponent until
 * no stones remain; so the current player loses. To illustrate:
 * <ul>
 * <li>current player moves to (1,2,1)
 * <li>opponent moves to (1,0,1)
 * <li>current player moves to (0,0,1)
 * <li>opponent moves to (0,0,0), and so wins.
 * </ul>
 * For how many positive integers n ≤ 230 does X(n,2n,3n) = 0 ?
 */
public class Euler301
{
    // --- Methods ---
    public static void main(String[] args)
    {
        final int max = 1_073_741_824;
        int res; // 2178309 = fibonacci(32)
        long delta;

        Utils.start();
        res = calc1(max);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static int calc1(int max)
    {
        int count = 0;
        for (int n = 1; n <= max; n++) {
            if (!x(n, n * 2, n * 3)) {
                count++;
            }
        }
        return count;
    }

    private static boolean x(int a, int b, int c)
    {
        return numSum(a, b, c) != 0;
    }

    /**
     * {@link https://en.wikipedia.org/wiki/Nim}
     */
    private static int numSum(int a, int b, int c)
    {
        return a ^ b ^ c;
    }

}