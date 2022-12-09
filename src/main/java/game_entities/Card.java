package game_entities;

/**
 * Card class that contains the rank and suit of a card.
 * Represents a regular playing card that also contains the image of the card
 */
public class Card implements Comparable<Card>{
    private final String rank;
    private final String suit;

    /**
     * Class constructor by specifying a rank and a suit
     * Use "A", "J", "Q", "K" for Ace, Jack, Queen, King respectively
     * For number cards, use their number value
     * Use the first letter of the suit as an upper case
     *
     * @param rank  rank of the card to be created
     * @param suit  suit of the card to be created
     */
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Class constructor by specifying a card using a String
     * The String should be formatted as "SuitRank"
     * eg. Ace of hearts would be "HA"
     * Use "A", "J", "Q", "K" for Ace, Jack, Queen, King respectively
     * For number cards, use their number value
     * Use the first letter of the suit as an upper case
     *
     * @param name  a string representing the card to be created
     */
    public Card(String name) {
        this.suit = name.substring(0, 1);
        this.rank = name.substring(1);
    }

    /**
     * Getter method for the rank of the card
     *
     * @return the rank of the card
     */
    public String getRank() {
        return rank;
    }

    /**
     * Getter method for the suit of the card
     *
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Return a string representation of the card
     * It will be represented as "SuitRank"
     * eg. if this.rank = "5" and this.suit = "H", "H5" will be returned
     *
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return this.getSuit() + this.getRank();
    }

    /**
     * Compare the suit of the card to another card
     * Returns true when this card has a greater suit, false otherwise
     *
     * @param card  the other card to be compared to
     * @return whether this card's suit is greater
     */
    public boolean compareSuit(Card card) {
        return this.getSuit().compareTo(card.getSuit()) > 0;
    }

    /**
     * Compare the suit of the card to another card
     * Returns true when they have the same suit, false otherwise
     *
     * @param card  the other card to be compared to
     * @return whether this card's suit is the same
     */
    public boolean sameSuit(Card card) {
        return this.getSuit().equalsIgnoreCase(card.getSuit());
    }

    /**
     * Helper method which returns the Rank as an integer
     * J, Q, K, A are 11, 12, 13, 14 respectively
     *
     * @return the rank of the card as an integer
     */
    public int getRankAsInt() {
        if (this.getRank().equalsIgnoreCase("J")) {
            return 11;
        } else if (this.getRank().equalsIgnoreCase("Q")) {
            return 12;
        } else if (this.getRank().equalsIgnoreCase("K")) {
            return 13;
        } else if (this.getRank().equalsIgnoreCase("A")) {
            return 14;
        } else {
            return Integer.parseInt(this.getRank());
        }
    }

    /**
     * Compare the rank of the card to another card
     * Returns true when this card has a greater rank, false otherwise
     *
     * @param card  the other card to be compared to
     * @return whether this card's rank is greater
     */
    public boolean compareRank(Card card) {
        return this.getRankAsInt() > card.getRankAsInt();
    }

    /**
     * Compare the rank of the card to another card
     * Returns true when they have the same rank, false otherwise
     *
     * @param card  the card to be compared to
     * @return whether they have the same rank
     */
    public boolean sameRank(Card card) {
        return this.getRankAsInt() == card.getRankAsInt();
    }

    /**
     * Returns a PNG representation of the card
     * Used in the display of the card
     *
     * @return a string with the PNG link for the card
     */
    public String getPNG() {

        String suit = "";
        String rank = "";

        // Dealing with suits
        if (this.getSuit().equalsIgnoreCase("D")) {
            suit = "diamonds";
        } else if (this.getSuit().equalsIgnoreCase("C")) {
            suit = "clubs";
        } else if (this.getSuit().equalsIgnoreCase("H")) {
            suit = "hearts";
        } else {
            suit = "spades";
        }

        // Dealing with rank
        if (this.getRank().equalsIgnoreCase("A")) {
            rank = "ace";
        } else if (this.getRank().equalsIgnoreCase("J")) {
            rank = "jack";
        } else if (this.getRank().equalsIgnoreCase("Q")) {
            rank = "queen";
        } else if (this.getRank().equalsIgnoreCase("K")) {
            rank = "king";
        } else {
            rank = this.getRank().toString();
        }

        return "images/Playing Cards/" + rank + "_of_" + suit + ".png";
    }

    /**Compare two cards by its rank.
     * the purpose of this method is to make game_entities.Card[] sort-able.
     *
     * @param other_card the object to be compared.
     * @return positive number if this hands is bigger than other,
     * negative if this is smaller, 0 if it is a tie.
     */
    @Override
    public int compareTo(Card other_card) {
        return this.getRankAsInt() - other_card.getRankAsInt();
    }
}
