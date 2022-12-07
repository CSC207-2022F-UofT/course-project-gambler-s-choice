package game_entities;

public class GameFactory implements GameFactoryInterface {
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
            cards[i] = new Card(tableCard[i]);
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
