package org.jorion.euler.util;

/**
 * Set of utility methods.
 */
public class Utils
{
    // --- Variables ---
    private static long start;

    // --- Methods ---
    /**
     * Start the chronometer.
     */
    public static void start()
    {
        start = System.nanoTime();
    }

    /**
     * @return the chronometer value.
     */
    public static long stop()
    {
        return System.nanoTime() - start;
    }

    /**
     * Display a formatted string.
     *
     * @param msg a free text
     * @param res a numerical result
     * @param delta the computation time (in µs)
     */
    public static void print(String msg, long res, long delta)
    {
        print(msg, Long.toString(res), delta);
    }

    public static void print(String msg, String res, long delta)
    {
        String nanosec = String.format("%8d ns", delta);
        String time = nanosec;
        delta /= 1000;
        if (delta > 0) {
            String microsec = String.format("%8d µs", delta);
            time = microsec;
            delta /= 1000;
        }
        if (delta > 0) {
            String millisec = String.format("%8d ms", delta);
            time = millisec;
        }
        System.out.println(msg + " - Result: " + res + " - time: " + time);
    }

    /**
     * @return True if the given number is palindromic in base 10.
     */
    public static boolean isPalindromic(int val)
    {
        return isPalindromic(Integer.toString(val));
    }

    /**
     * @return True if the given string is palindromic.
     */
    public static boolean isPalindromic(String str)
    {
        int len = str.length();
        boolean flag = true;
        for (int i = 0; i < (len + 1) / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Formula: C<sup>p</sup><sub>n</sub> = n! / (p! * (n - p)!)
     * 
     * <pre>
     * denombrement_combinaisons( p , n ) {
     * 	si (p = n) retourner 1;
     * 	si (p > n/2) p = n-p;
     * 	res = n-p+1;
     * 	pour i = 2 par 1 tant que i < = p
     * 	res = res * (n-p+i)/i;
     * 	fin pour
     * 	retourner res;
     * </pre>
     */
    public static long combinationCPN(int p, int n)
    {
        if (p == n) {
            return 1;
        }
        if (p > n / 2) {
            p = n - p;
        }
        long res = n - p + 1;
        for (int i = 2; i <= p; i++) {
            res = res * (n - p + i) / i;
        }
        return res;
    }

}
