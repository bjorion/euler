package org.jorion.euler.problem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.jorion.euler.util.Utils;

/**
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
 * 
 * <pre>
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * </pre>
 * 
 * By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we
 * form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle
 * number then we shall call the word a triangle word.
 * <p>
 * Using p042_words.txt, a 16K text file containing nearly two-thousand common English words, how many are triangle
 * words?
 */
public class Euler042
{
    // --- Variables ---
    private static final int ASCII_0 = 64;

    // --- Methods ---
    public static void main(String[] args)
            throws Exception
    {
        int res;
        long delta;

        String line = null;
        URL url = Euler022.class.getClassLoader().getResource("p042_words.txt");
        File file = new File(url.getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            line = br.readLine();
        }

        Utils.start();
        res = calc1(line);
        delta = Utils.stop();
        Utils.print("Boolean array (good) ", res, delta);

        Utils.start();
        res = calc2(line);
        delta = Utils.stop();
        Utils.print("Brute force (bad)    ", res, delta);

        Utils.start();
        res = calc3(line);
        delta = Utils.stop();
        Utils.print("Stream (worse)       ", res, delta);
    }

    /**
     * The longest word is less than 20 chars. Worse case for a value is thus 20 * 26 (='Z') = 520. To be on the safe
     * side, let's say the max value is 1000.
     * <p>
     * Let's create an array of boolean for every number from 1 to 1000 and mark as true the triangle number. Advantage:
     * we do that only once.
     */
    private static int calc1(String line)
    {
        int max = 500;
        boolean[] arr = new boolean[max];
        int maxi = (int)Math.sqrt(max * 2); // approximation of n such as n(n+1)/2 = max 
        for (int i = 1; i <= maxi; i++) {
            int sum = i * (i + 1) / 2;
            arr[sum] = true;
        }

        int count = 0;
        String[] words = line.split(",");
        for (String word : words) {
            int sum = 0;
            for (int i = 1; i < word.length() - 1; i++) {
                sum += (word.charAt(i) - ASCII_0);
            }
            if (arr[sum]) {
                count++;
            }
        }
        return count;
    }

    private static int calc2(String line)
    {
        int count = 0;
        String[] words = line.split(",");
        for (String word : words) {
            int sum = 0;
            for (int i = 1; i < word.length() - 1; i++) {
                sum += (word.charAt(i) - ASCII_0);
            }
            double val = (1 + Math.sqrt(1 + 8 * sum)) / 2;
            if (val == ((int) val)) {
                count++;
            }
        }
        return count;
    }

    private static int calc3(String line)
    {
        line = line.replaceAll("\"", "");
        // @formatter:off
        long count = Arrays.stream(line.split(","))
                .parallel()
                .map(w -> w.chars().map(c -> c - ASCII_0).sum())
                .filter(i -> IntStream.rangeClosed(0, i).anyMatch(j -> i == 0.5 * j * (j + 1)))
                .count();
        // @formatter:on
        return (int) count;
    }
}
