package org.jorion.euler.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Set of utility methods related to prime numbers. Use {@link MathUtils}.
 */
public class PrimeUtils {

    static void wakeUp() {
    }

    /**
     * Find if the given number is prime. We test all the odd numbers as divisors.
     *
     * @param n the value to test
     * @return True if the given value is prime, false otherwise
     */
    public static boolean isPrime(long n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n < 2) {
            return false;
        }

        boolean result = true;
        final long sqrt = (long) Math.sqrt(n);
        long a = 3;
        while (a <= sqrt) {
            if (n % a == 0) {
                result = false;
                break;
            }
            a += 2;
        }
        return result;
    }

    /**
     * Find if the given number is prime. We test all the multiples of 6 +/- 1 as divisors (all primes, except 3,
     * satisfy the condition 6n+/-1)
     * <p>
     * This algorithm should be more performant than <code>isPrime(n)</code> (we check 33% of the numbers instead of
     * 50%).
     *
     * @param n the value to test
     * @return True if the given value is prime, false otherwise
     */
    public static boolean isPrime6(long n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0 || n < 2) {
            return false;
        }

        boolean done = false;
        boolean result = true;
        long sqrt = (long) Math.sqrt(n);
        long k = 0;

        while (!done) {
            k++;
            for (int i = -1; i <= 1; i = i + 2) {
                long val = k * 6 + i;
                if (val <= sqrt) {
                    if (n % val == 0) {
                        result = false;
                        done = true;
                        break;
                    }
                } else {
                    done = true;
                }
            }
        }
        return result;
    }

    /**
     * Use the Sieve of Eratosthenes to find all primes between 1 and n.
     *
     * @param n the upper limit for the sieve
     * @return an array of booleans, true for a prime
     */
    public static boolean[] isPrimeSoE(int n) {
        // initially assume all integers are prime
        boolean[] isPrimes = new boolean[n + 1];
        isPrimes[2] = true;
        for (int i = 3; i <= n; i++) {
            // for even numbers: false; for odd numbers: true
            isPrimes[i] = ((i & 0b1) == 1);
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 3; factor * factor <= n; factor += 2) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ..., n/factor
            if (isPrimes[factor]) {
                for (int j = factor; factor * j <= n; j++) {
                    isPrimes[factor * j] = false;
                }
            }
        }
        return isPrimes;
    }

    @SuppressWarnings("unused")
    public static Set<Integer> isPrimeSoEAsSet(int n) {
        boolean[] primes = isPrimeSoE(n);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < primes.length; i++) {
            if (primes[i]) {
                set.add(i);
            }
        }
        return set;
    }

    /**
     * Find all the prime factors of the given integer.
     *
     * @param n the integer
     * @return A map with key = prime factor and value = number of occurrences (>= 1)
     */
    public static Map<Integer, Integer> findPrimeFactors(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int div = 2;
        while (n > 1) {
            int count = 0;
            while (n % div == 0) {
                n = n / div;
                count++;
            }
            if (count > 0) {
                map.put(div, count);
            }
            div = (div == 2) ? 3 : div + 2;
        }
        return map;
    }

    /**
     * Alternative to {@link #findPrimeFactors(int)} when we already know the primes up to n.
     *
     * @param n      the integer
     * @param primes an array of boolean, value of index 'n' is true if 'n' is a prime
     * @return A map with key = prime factor and value = number of occurrences (>= 1)
     */
    public static Map<Integer, Integer> findPrimeFactors(int n, final boolean[] primes) {
        boolean stopPrime = false;
        Map<Integer, Integer> map = new HashMap<>();
        if (primes.length > n && primes[n]) {
            map.put(n, 1);
            return map;
        }

        int div = 2;
        while (n > 1) {
            int count = 0;
            while (n % div == 0) {
                n = n / div;
                count++;
            }
            if (count > 0) {
                map.put(div, count);
            }
            if (!stopPrime) {
                int next = Utils.nextTrue(primes, div);
                if (next > 0) {
                    div = next;
                } else {
                    System.out.println("WARN: primes[] to small");
                    stopPrime = true;
                    div += 2;
                }
            } else {
                div += 2;
            }
        }
        return map;
    }

    /**
     * Compute Euler's phi function.
     * cf. <a href="https://en.wikipedia.org/wiki/Euler%27s_totient_function">...</a>
     *
     * @param n      an integer
     * @param primes an array of boolean, value of index 'n' is true if 'n' is a prime
     * @return phi(n)
     */
    public static int phi(int n, final boolean[] primes) {
        // phi(n) = n - 1 if n is prime
        if (primes[n]) {
            return n - 1;
        }

        Map<Integer, Integer> map = findPrimeFactors(n, primes);
        int product = 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int prime = entry.getKey();
            int factor = entry.getValue();
            if (factor > 1) {
                product *= (int) Math.pow(prime, factor - 1);
            }
            product *= (prime - 1);
        }
        return product;
    }

}
