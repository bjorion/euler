package org.jorion.euler.problem;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.jorion.euler.util.Utils;

/**
 * In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
 * <ul>
 * <li><b>High Card</b>: Highest value card.
 * <li><b>One Pair</b>: Two cards of the same value.
 * <li><b>Two Pairs</b>: Two different pairs.
 * <li><b>Three of a Kind</b>: Three cards of the same value.
 * <li><b>Straight</b>: All cards are consecutive values. (ok)
 * <li><b>Flush</b>: All cards of the same suit. (ok)
 * <li><b>Full House</b>: Three of a kind and a pair.
 * <li><b>Four of a Kind</b>: Four cards of the same value.
 * <li><b>Straight Flush</b>: All cards are consecutive values of same suit. (ok)
 * <li><b>Royal Flush</b>: Ten, Jack, Queen, King, Ace, in same suit. (ok)
 * </ul>
 * The cards are valued in the order: 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 * <p>
 * If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of
 * eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of
 * queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next
 * highest cards are compared, and so on.
 * <p>
 * Consider the following five hands dealt to two players:
 * 
 * <pre>
 * Hand        Player 1        Player 2        Winner
 *  1       5H 5C 6S 7S KD  2C 3S 8S 8D TD      Player 2
 *          Pair of Fives   Pair of Eights
 *
 *  2       5D 8C 9S JS AC  2C 5C 7D 8S QH
 *          High card Ace   Highest card Queen  Player 1
 *          
 *  3       2D 9C AS AH AC  3D 6D 7D TD QD
 *          Three Aces      Flush with Diamonds Player 2
 *          
 *  4       4D 6S 9H QH QC  3D 6D 7H QD QS
 *          Pair of Queens  Pair of Queens      Player 1
 *          High card Nine  Highest card Seven
 *          
 *  5       2H 2D 4C 4D 4S  3C 3D 3S 9S 9D      Player 1
 *          Full House      Full House
 *          With Three Four with Three Threes
 * </pre>
 * 
 * The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten
 * cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You
 * can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific
 * order, and in each hand there is a clear winner.
 * <p>
 * How many hands does Player 1 win?
 */
public class Euler054
{
    // --- Constants ---
    private static final int N = 5;

    // --- Methods ---
    public static void main(String[] args)
            throws Exception
    {
        long res;
        long delta;

        List<String> lines = Utils.readLines("p054_poker.txt");

        Utils.start();
        res = calc1(lines);
        delta = Utils.stop();
        Utils.print("Algorithm ", res, delta);
    }

    private static long calc1(List<String> lines)
    {
        String[] cards = lines.get(0).split(" ");
        String[] cards1 = new String[N];
        String[] cards2 = new String[N];
        System.arraycopy(cards, 0, cards1, 0, N);
        System.arraycopy(cards, N, cards2, 0, N);

        Hand p1 = new Hand(cards1);
        Hand p2 = new Hand(cards2);
        System.out.println(p1.toString());
        System.out.println(p2.toString());

        // Hand p = new Hand(new String[] { "9S", "QS", "JS", "KS", "TS" });
        // p.analyse();
        // System.out.println(p.toString() + ":" + p.isFlush() + ":" + p.isRoyalFlush() + ":" + p.isStraitFlush());
        
        Hand p = new Hand(new String[] { "9S", "8C", "9D", "KS", "9H" });
        p.analyse();
        System.out.println(p.fourKind());
        

        return 0;
    }

    static class Hand
    {
        private final Set<Card> cards = new TreeSet<>();

        private boolean strait;

        private boolean flush;

        private boolean straitFlush;

        private boolean royalFlush;

        private Map<Integer, Integer> map;

        // -- Methods ---
        Hand(String[] cardsAsStr)
        {
            for (int i = 0; i < N; i++) {
                String cardAsStr = cardsAsStr[i];
                Card card = new Card(cardAsStr);
                cards.add(card);
            }
            assert cards.size() == N;
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            for (Card card : cards) {
                sb.append(card.toString()).append(" ");
            }
            return sb.toString();
        }

        void analyse()
        {
            sortCards();
            strait();
            flush();
            straitFlush();
            royalFlush();
        }

        /**
         * All cards are consecutive values.
         */
        private void strait()
        {
            boolean flag = true;
            Iterator<Card> iter = cards.iterator();
            int val = -1;
            while (iter.hasNext()) {
                Card card = iter.next();
                if (val == -1) {
                    val = card.getVal();
                }
                if (card.getVal() != val) {
                    flag = false;
                    break;
                }
                val++;
            }
            this.strait = flag;
        }

        /**
         * All cards are of the same suit.
         */
        private void flush()
        {
            boolean flag = true;
            Iterator<Card> iter = cards.iterator();
            Suit suit = null;
            while (iter.hasNext()) {
                Card card = iter.next();
                if (suit == null) {
                    suit = card.getSuit();
                }
                else if (card.getSuit() != suit) {
                    flag = false;
                    break;
                }
            }
            this.flush = flag;
        }

        /**
         * Sort cards
         */
        private void sortCards()
        {
            map = new TreeMap<>();
            Iterator<Card> iter = cards.iterator();
            while (iter.hasNext()) {
                int key = iter.next().getVal();
                Integer value = map.get(key);
                if (value == null) {
                    map.put(key, 1);
                }
                else {
                    map.put(key, value.intValue() + 1);
                }
            }
        }

        /**
         * Four of a kind.
         */
        public int fourKind()
        {
            int val = -1;
            for (Entry<Integer, Integer> es: map.entrySet()) {
                if (es.getValue() == 4) {
                    val = es.getKey();
                }
            }
            return val;
        }

        /**
         * All cards are consecutive values of same suit.
         */
        private void straitFlush()
        {
            this.straitFlush = this.strait && this.flush;
        }

        private void royalFlush()
        {
            if (straitFlush) {
                this.royalFlush = (cards.iterator().next().getVal() == 10);
            }
        }

        // --- Getters ---
        boolean isFlush()
        {
            return flush;
        }

        boolean isStrait()
        {
            return strait;
        }

        boolean isStraitFlush()
        {
            return straitFlush;
        }

        boolean isRoyalFlush()
        {
            return royalFlush;
        }
    }

    static enum Suit {
        S, H, D, C;
    }

    static class Card implements Comparable<Card>
    {
        private final Suit suit;

        /* 2..10 J Q K A */
        private final String value;

        private final int val;

        // --- Methods ---
        Card(String card)
        {
            suit = Suit.valueOf(card.substring(1, 2));
            value = card.substring(0, 1);
            if ("A".equals(value)) {
                val = 14;
            }
            else if ("K".equals(value)) {
                val = 13;
            }
            else if ("Q".equals(value)) {
                val = 12;
            }
            else if ("J".equals(value)) {
                val = 11;
            }
            else if ("T".equals(value)) {
                val = 10;
            }
            else {
                val = Integer.parseInt(value);
            }
        }

        @Override
        public String toString()
        {
            return value + suit.name();
        }

        @Override
        public int compareTo(Card that)
        {
            int compare;
            if (this.getVal() < that.getVal()) {
                compare = -1;
            }
            else if (this.getVal() > that.getVal()) {
                compare = 1;
            }
            else {
                compare = this.getSuit().compareTo(that.getSuit());
            }
            return compare;
        }

        // --- Getters ---
        int getVal()
        {
            return val;
        }

        String getValue()
        {
            return value;
        }

        Suit getSuit()
        {
            return suit;
        }
    }
}