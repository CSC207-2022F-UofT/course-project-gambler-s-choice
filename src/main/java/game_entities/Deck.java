package game_entities;

import game_entities.Card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
        // A = ace, X = 10, J = jack, Q = queen, K = king
        String[] cardsString = {"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DX", "DJ", "DQ", "DK",
                "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CX", "CJ", "CQ", "CK",
                "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "HX", "HJ", "HQ", "HK",
                "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "SX", "SJ", "SQ", "SK"};
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
}
