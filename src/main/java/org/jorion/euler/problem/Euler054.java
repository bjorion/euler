package org.jorion.euler.problem;

import java.util.ArrayList;
import java.util.Arrays;
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
 * <li><b>Straight</b>: All cards are consecutive values.
 * <li><b>Flush</b>: All cards of the same suit.
 * <li><b>Full House</b>: Three of a kind and a pair.
 * <li><b>Four of a Kind</b>: Four cards of the same value.
 * <li><b>Straight Flush</b>: All cards are consecutive values of same suit.
 * <li><b>Royal Flush</b>: Ten, Jack, Queen, King, Ace, in same suit.
 * </ul>
 * The cards are valued in the order: 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 * <p>
 * If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see
 * example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example
 * 4 below); if the highest cards tie then the next highest cards are compared, and so on.
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
 * The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single
 * space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters
 * or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.
 * <p>
 * How many hands does Player 1 win?
 */
public class Euler054 {

	// --- Constants ---
	private static final int N = 5;

	// --- Methods ---
	public static void main(String[] args)
			throws Exception {

		long res;
		long delta;

		List<String> lines = Utils.readLines("p054_poker.txt");

		Utils.start();
		res = calc1(lines);
		delta = Utils.stop();
		Utils.print("Algorithm ", res, delta);
	}

	private static long calc1(List<String> lines) {

		int first = 0;
		int second = 0;
		int tie = 0;

		for (String line : lines) {
			String[] cards = line.split(" ");
			String[] cards1 = new String[N];
			String[] cards2 = new String[N];
			System.arraycopy(cards, 0, cards1, 0, N);
			System.arraycopy(cards, N, cards2, 0, N);
			Hand h1 = new Hand(cards1);
			h1.analyse();
			Hand h2 = new Hand(cards2);
			h2.analyse();

			System.out.println(Arrays.toString(cards1) + " vs " + Arrays.toString(cards2));
			int res = h1.compareTo(h2);
			if (res > 0) {
				first++;
			}
			else if (res < 0) {
				second++;
			}
			else {
				tie++;
			}
			System.out.println(res);
		}
		System.out.println("first: " + first + ", second: " + second + ", tie: " + tie);
		return first;
	}

	@SuppressWarnings("unused")
	private static long calc2(List<String> lines) {

		lines = new ArrayList<>();
		lines.add("5H 5C 6S 7S KD 2C 3S 8S 8D TD");
		lines.add("5D 8C 9S JS AC 2C 5C 7D 8S QH");
		lines.add("2D 9C AS AH AC 3D 6D 7D TD QD");
		lines.add("4D 6S 9H QH QC 3D 6D 7H QD QS");
		lines.add("2H 2D 4C 4D 4S 3C 3D 3S 9S 9D");
		lines.add("2H 3D 4C 5D AS 2D 3H 4D 5C AH");

		return calc1(lines);
	}

	/**
	 * A Hand of 5 cards.
	 */
	static class Hand implements Comparable<Hand> {

		/** Set of cards ordering according to their descending value. */
		private final Set<Card> cards = new TreeSet<>();

		private int firstPair;

		private int secondPair;

		private int threeOfAKind;

		private boolean strait;

		private boolean flush;

		private boolean fullHouse;

		private int fourOkAKind;

		private boolean straitFlush;

		/** Count how many times a given value appears (key/value)=(value/count). */
		private Map<Integer, Integer> map;

		// -- Methods ---
		Hand(String[] cardsAsStr) {

			for (int i = 0; i < N; i++) {
				String cardAsStr = cardsAsStr[i];
				Card card = new Card(cardAsStr);
				cards.add(card);
			}
			assert cards.size() == N;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();
			for (Card card : cards) {
				sb.append(card.toString()).append(" ");
			}
			return sb.toString();
		}

		void analyse() {

			sortCards();
			checkFirstPair();
			checkSecondPair();
			checkThreeOfAKind();
			checkStrait();
			checkFlush();
			checkFullHouse();
			checkFourOfAKind();
			checkStraitFlush();
		}

		/**
		 * @return -1 if {@code this} has a lesser value than {@code that}, +1 if it has a higher value, 0 if they have the same value
		 */
		@Override
		public int compareTo(Hand that) {

			int res = 0;

			// Strait Flush (5 cards)
			if (this.isStraitFlush() || that.isStraitFlush()) {
				res = Boolean.compare(this.isStraitFlush(), that.isStraitFlush());
				if (res == 0) {
					// comparing the highest card
					res = this.compareCard(that);
				}
				return res;
			}

			// Square (4 cards, no equality possible)
			if (this.getFourOkAKind() > 0 || that.getFourOkAKind() > 0) {
				res = Integer.compare(this.getFourOkAKind(), that.getFourOkAKind());
				Utils.assertThat(res != 0, "Square");
				return res;
			}

			// Full House (brelan + pair)
			if (this.isFullHouse() || that.isFullHouse()) {
				res = Boolean.compare(this.isFullHouse(), that.isFullHouse());
				if (res == 0) {
					// if both full house, compare brelan (no equality possible)
					res = Integer.compare(this.getThreeOfAKind(), that.getThreeOfAKind());
					Utils.assertThat(res != 0, "Full House");
				}
				return res;
			}

			// Flush (no brelan or pair possible)
			if (this.isFlush() || that.isFlush()) {
				res = Boolean.compare(this.isFlush(), that.isFlush());
				if (res == 0) {
					// if both flush, compare the highest card
					res = this.compareCard(that);
				}
				return res;
			}

			// Strait (no brelan or pair possible)
			if (this.isStrait() || that.isStrait()) {
				res = Boolean.compare(this.isStrait(), that.isStrait());
				if (res == 0) {
					// if both strait, compare the highest card
					res = this.compareCard(that);
				}
				return res;
			}

			// Brelan (no pair possible)
			if (this.getThreeOfAKind() > 0 || that.getThreeOfAKind() > 0) {
				res = Integer.compare(this.getThreeOfAKind(), that.getThreeOfAKind());
				Utils.assertThat(res != 0, "Brelan");
				return res;
			}

			// Pairs (no brelan possible)
			int thisPair = this.getFirstPair();
			int thatPair = that.getFirstPair();
			int thisPair2 = this.getSecondPair();
			int thatPair2 = that.getSecondPair();

			// Double pairs
			if (thisPair2 > 0 || thatPair2 > 0) {
				res = Integer.compare(thisPair, thatPair);
				// check second pair
				if (res == 0) {
					res = Integer.compare(thisPair2, thatPair2);
					if (res == 0) {
						this.compareCard(that);
					}
				}
				return res;
			}

			// Single pair
			if (thisPair > 0 || thatPair > 0) {
				res = Integer.compare(thisPair, thatPair);
				if (res == 0) {
					res = this.compareCard(that);
				}
				return res;
			}

			// Highest card
			res = this.compareCard(that);
			return res;
		}

		private int compareCard(Hand that) {

			int res = 0;
			Iterator<Card> thisIter = this.cards.iterator();
			Iterator<Card> thatIter = that.cards.iterator();
			while (thisIter.hasNext()) {
				Card thisCard = thisIter.next();
				Card thatCard = thatIter.next();
				res = Integer.compare(thisCard.getVal(), thatCard.getVal());
				if (res != 0) {
					break;
				}
			}
			return res;
		}

		/**
		 * Sort cards according to their descending value.
		 */
		private void sortCards() {

			// TreeMap with a descending comparator
			map = new TreeMap<>((a, b) -> {
				return b.compareTo(a);
			});

			Iterator<Card> iter = cards.iterator();
			while (iter.hasNext()) {
				int key = iter.next().getVal();
				Integer value = map.get(key);
				int val = (value == null) ? 0 : value.intValue();
				map.put(key, val + 1);
			}
		}

		private void checkFirstPair() {

			this.firstPair = nOfAKind(2, 1);
		}

		private void checkSecondPair() {

			this.secondPair = nOfAKind(2, 2);
		}

		/**
		 * Three of a kind.
		 *
		 * @return 0 if none, the value (present 3 times) if found
		 */
		private void checkThreeOfAKind() {

			this.threeOfAKind = nOfAKind(3, 1);
		}

		/**
		 * Check if all cards are consecutive values.
		 */
		private void checkStrait() {

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
		 * Check if all cards are of the same suit.
		 */
		private void checkFlush() {

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

		private void checkFullHouse() {

			this.fullHouse = (this.threeOfAKind > 0) && (this.firstPair > 0);
		}

		/**
		 * Check Four of a kind.
		 *
		 * @return 0 if none, the value (present four times) if found
		 */
		private void checkFourOfAKind() {

			this.fourOkAKind = nOfAKind(4, 1);
		}

		/**
		 * Check if all cards are consecutive values of same suit.
		 */
		private void checkStraitFlush() {

			this.straitFlush = this.strait && this.flush;
		}

		/**
		 * @param position 1 for the highest combination, 2 for the second highest
		 * @param number the number of cards (2 for a pair, 3 for a brelan, 4 for a square)
		 * @return the nth highest card, or 0 if not found
		 */
		private int nOfAKind(int number, int position) {

			int val = 0, count = 0;
			for (Entry<Integer, Integer> es : map.entrySet()) {
				int tmp = 0;
				if (es.getValue() == number) {
					tmp = es.getKey();
					count++;
				}
				if (count >= position) {
					val = tmp;
					break;
				}
			}
			return val;
		}

		// --- Getters ---
		int getFirstPair() {

			return firstPair;
		}

		int getSecondPair() {

			return secondPair;
		}

		int getThreeOfAKind() {

			return threeOfAKind;
		}

		boolean isStrait() {

			return strait;
		}

		boolean isFlush() {

			return flush;
		}

		boolean isFullHouse() {

			return fullHouse;
		}

		int getFourOkAKind() {

			return fourOkAKind;
		}

		boolean isStraitFlush() {

			return straitFlush;
		}

	}

	static enum Suit {

		/** Spade. */
		S,

		/** Heart. */
		H,

		/** Diamond. */
		D,

		/** Clover. */
		C;
	}

	/**
	 * A Card (2-As, with a Suit)
	 */
	static class Card implements Comparable<Card> {

		// --- Constants ---
		private static final String AS = "A";

		private static final String KING = "K";

		private static final String QUEEN = "Q";

		private static final String JACK = "J";

		private static final String TEN = "T";

		private final Suit suit;

		/* 2..10 J Q K A */
		private final String value;

		private final int val;

		// --- Methods ---
		Card(String card) {

			suit = Suit.valueOf(card.substring(1, 2));
			value = card.substring(0, 1);
			switch (value) {
			case AS:
				val = 14;
				break;
			case KING:
				val = 13;
				break;
			case QUEEN:
				val = 12;
				break;
			case JACK:
				val = 11;
				break;
			case TEN:
				val = 10;
				break;
			default:
				val = Integer.parseInt(value);
			}
		}

		@Override
		public String toString() {

			return value + suit.name();
		}

		@Override
		public int compareTo(Card that) {

			int compare;
			if (this.getVal() < that.getVal()) {
				compare = 1;
			}
			else if (this.getVal() > that.getVal()) {
				compare = -1;
			}
			else {
				compare = this.getSuit().compareTo(that.getSuit());
			}
			return compare;
		}

		// --- Getters ---
		int getVal() {

			return val;
		}

		String getValue() {

			return value;
		}

		Suit getSuit() {

			return suit;
		}
	}
}