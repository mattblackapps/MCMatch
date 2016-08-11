package com.mygdx.mcmatch.com.mygdx.mcmatch.play;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

/**
 * Created by MC on 8/10/16.
 * Singleton for maintaining a single store (deck) of matched text for matching cards
 * Also generates the next text from a "shuffled" deck
 */



public class Dealer {
	public static Dealer getInstance() {
		return ourInstance;
	}
	public static final int MAX = 10;
	public static final int SHUFFLE_REPS = 5;

	private static Dealer ourInstance = new Dealer();
	private List<String> deck = new ArrayList<String>(MAX);
	private List<String> shuffledDeck = new ArrayList<String>(MAX);


	public String getNextFor(int numberOfCards) {
		if(shuffledDeck.isEmpty()) {
			generateDeck();
			shuffleDeck(numberOfCards);
		}

		return pop(shuffledDeck);
	}

	private Dealer() {

		generateDeck();

	}

	private void generateDeck() {
		deck.add("Lion");
		deck.add("Cow");
		deck.add("Elephant");
		deck.add("Sheep");
		deck.add("Monkey");
		deck.add("Kitty");
		deck.add("Dog");
		deck.add("Chicken");
		deck.add("Frog");
		deck.add("Bird");
	}

	private void shuffleDeck(int numberOfCards) {
		int n = convert(numberOfCards);
		System.out.println("Shuffling deck for " + n + " matches");
		for(int i = 0; i < n; i++) {
			System.out.println("Adding: " + deck.get(i));
			shuffledDeck.add(deck.get(i));
			shuffledDeck.add(deck.get(i));
		}

		for(int i = 0; i < SHUFFLE_REPS; i++) {
			System.out.println("****** Shuffle rep: " + Integer.toString(i+1) + " *******");
			shuffle(numberOfCards);
		}
	}

	private void shuffle(int numberOfCards) {
		int n = numberOfCards;
		Random random = new Random();
		for(int i = n-1 ; i > 0; i--) {
			int j = random.nextInt(i);
			System.out.println("Swapping " + i + " and " + j);
			Collections.swap(shuffledDeck, i, j);
		}
	}

	private int convert(int n) {
		if (n > MAX * 2) {
			System.out.println("Max (" + Integer.toString(MAX) + ") exceeded with request for " +
					Integer.toString(n) + ". Defaulting to maximum");
		}
		return Math.min(MAX, (int)Math.floor(n/2));
	}

	private String pop(List<String> list) {
		if (list.isEmpty()) { System.out.println("Nothing to pop!"); return null; }
		String s = list.get(list.size()-1);
		list.remove(list.size()-1);
		return s;
	}
}
