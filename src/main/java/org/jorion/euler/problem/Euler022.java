package org.jorion.euler.problem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.jorion.euler.util.Utils;

/**
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first
 * names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply
 * this value by its alphabetical position in the list to obtain a name score.
 * <p>
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the
 * 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
 * <p>
 * What is the total of all the name scores in the file?
 */
public class Euler022
{
    // --- Variables ---
    private static final int ASCII_0 = 64;

    // --- Methods ---
    public static void main(String[] args)
            throws Exception
    {
        long res;
        long delta;

        String line = null;
        URL url = Euler022.class.getClassLoader().getResource("p022_names.txt");
        File file = new File(url.getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            line = br.readLine();
        }

        Utils.start();
        res = calc1(line);
        delta = Utils.stop();
        Utils.print("TreeSet ", res, delta);

        Utils.start();
        res = calc2(line);
        delta = Utils.stop();
        Utils.print("Arrays  ", res, delta);

    }

    private static long calc1(String line)
    {
        long res = 0;
        StringTokenizer st = new StringTokenizer(line, ",");
        Set<String> names = new TreeSet<>();
        while (st.hasMoreTokens()) {
            String name = st.nextToken();
            names.add(name);
        }
        // System.out.println("length: " + names.size());

        int index = 1;
        for (String name : names) {
            int value = computeValue(name);
            int score = value * index;
            res += score;
            index++;
        }
        return res;
    }

    private static long calc2(String line)
    {
        long res = 0;
        String[] names = line.split(",");
        Arrays.sort(names);

        int index = 1;
        for (String name : names) {
            int value = computeValue(name);
            int score = value * index;
            res += score;
            index++;
        }
        return res;
    }

    private static int computeValue(String name)
    {
        int res = 0;
        for (int i = 1; i < name.length() - 1; i++) {
            res += (name.charAt(i) - ASCII_0);
        }
        return res;
    }
}
