package game_entities;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Represents a deck used to store cards
 * Uses the card class to store card information
 */
public class Deck {
    private Stack<Card> deck;

    /**
     * Class constructor
     */
    public Deck() {
        this.createDeck();
        this.shuffle();
    }

    public Deck(Card[] cards) {
        this.deck = new Stack<>();
        this.deck.addAll(List.of(cards));
    }

    /**
     * Helper method used in the creation of the deck
     */
    private void createDeck() {
        // Format of cards
        // S = spades, H = hearts, C = clubs, D = diamonds
        // A = ace, J = jack, Q = queen, K = king
        String[] cardsString = {"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
                "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
                "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
                "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
        Card[] cards = new Card[52];
        for (int i = 0; i < 52; i++) {
            cards[i] = new Card(cardsString[i]);
        }
        this.deck = new Stack<>();
        this.deck.addAll(List.of(cards));
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    /**
     * Determine whether the deck is empty
     * Returns true when the deck is empty, false when non-empty
     *
     * @return whether the deck is empty
     */
    public boolean isEmpty() {
        return this.deck.isEmpty();
    }

    /**
     * Pops the top card off the deck
     *
     * @return the top card from the deck
     */
    public Card getCard() {
        return this.deck.pop();
    }

    /**
     * Returns the deck as a string list
     * @return String array of cards
     */
    public String[] deckAsStringArray() {
        int len = deck.size();
        Card[] tempDeck = new Card[len];
        String[] toReturn = new String[len];
        for (int i = len - 1; i >= 0; i--) {
            Card card = deck.pop();
            tempDeck[i] = card;
            toReturn[i] = card.toString();
        }
        this.deck.addAll(List.of(tempDeck));
        return toReturn;
    }
}
