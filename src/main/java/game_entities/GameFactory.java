package game_entities;

/**
 * Gamefactory class which is a factory to create Game Objects.
 *
 */
public class GameFactory implements GameFactoryInterface {
    /**
     * Creates a game with the given parameters
     * @param currentPlayer the current player
     * @param firstPlayer the first player of the round
     * @param lastToBet the first one to bet (last to call)
     * @param playerBalance the balance of all the players as an array
     * @param card1 the first card of every player as an array
     * @param card2 the second card of every player as an array
     * @param tableCard the cards on the table
     * @param currentBet the current bet
     * @param isActive an array of active players
     * @param playerBets an array of player bets
     * @param deck the deck as strings
     * @return a new game object with the inputted parameters
     */
    @Override
    public GameInterface create(int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance,
                                String[] card1, String[] card2, String[] tableCard, int currentBet,
                                boolean[] isActive, int[] playerBets, String[] deck) {

        Player[] players = new Player[card1.length];

        for (int i= 0; i < players.length; i++) {
            players[i] = new Player(new Card(card1[i]), new Card(card2[i]));
            players[i].addMoney(playerBalance[i]);
        }

        Card[] cards = new Card[tableCard.length];
        for (int i = 0; i < cards.length; i++) {
            if (tableCard[i] != null) {
                cards[i] = new Card(tableCard[i]);
            }
        }

        Card[] deckCards = new Card[deck.length];

        for (int i = 0; i < deckCards.length; i++) {
            deckCards[i] = new Card(deck[i]);
        }

        Deck cardDeck = new Deck(deckCards);

        Pool pool = new Pool(players, playerBets);

        return new Game(players, currentPlayer, firstPlayer, lastToBet, cards, currentBet, isActive, pool, cardDeck);
    }
}
