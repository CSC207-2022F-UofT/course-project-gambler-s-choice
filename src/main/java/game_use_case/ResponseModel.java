package game_use_case;

/**
 * Represents the Response Model. Will contain the primitive values used to represent each game instance.
 * The game screen will use this to construct a new game instance based off the current variables
 */
public class ResponseModel {

    int currentPlayer;
    int firstPlayer;
    int lastToBet;
    int[] playerBalance;
    String[] card1;
    String[] card2;
    String[] tableCard;
    String[] card1PNG;
    String[] card2PNG;
    String[] tableCardPNG;
    int currentBet;
    boolean[] isActive;
    int[] playerBets;
    String[] deck;
    boolean isInteract;

    /**
     * Response Constructor
     */
    public ResponseModel(int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance, String[] card1,
                         String[] card2, String[] tableCard, String[] card1PNG, String[] card2PNG,
                         String[] tableCardPNG, int currentBet, boolean[] isActive, int[] playerBets,
                         String[] deck, boolean isInteract) {
        this.currentPlayer = currentPlayer;
        this.firstPlayer = firstPlayer;
        this.lastToBet = lastToBet;
        this.playerBalance = playerBalance;
        this.card1 = card1;
        this.card2 = card2;
        this.tableCard = tableCard;
        this.card1PNG = card1PNG;
        this.card2PNG = card2PNG;
        this.tableCardPNG = tableCardPNG;
        this.currentBet = currentBet;
        this.isActive = isActive;
        this.playerBets = playerBets;
        this.deck = deck;
        this.isInteract = isInteract;
    }
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(int firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public int getLastToBet() {
        return lastToBet;
    }

    public void setLastToBet(int lastToBet) {
        this.lastToBet = lastToBet;
    }

    public int[] getPlayerBalance() {
        return playerBalance;
    }

    public void setPlayerBalance(int[] playerBalance) {
        this.playerBalance = playerBalance;
    }

    public String[] getCard1() {
        return card1;
    }

    public void setCard1(String[] card1) {
        this.card1 = card1;
    }

    public String[] getCard2() {
        return card2;
    }

    public void setCard2(String[] card2) {
        this.card2 = card2;
    }

    public String[] getTableCard() {
        return tableCard;
    }

    public void setTableCard(String[] tableCard) {
        this.tableCard = tableCard;
    }

    public String[] getCard1PNG() {
        return card1PNG;
    }

    public void setCard1PNG(String[] card1PNG) {
        this.card1PNG = card1PNG;
    }

    public String[] getCard2PNG() {
        return card2PNG;
    }

    public void setCard2PNG(String[] card2PNG) {
        this.card2PNG = card2PNG;
    }

    public String[] getTableCardPNG() {
        return tableCardPNG;
    }

    public void setTableCardPNG(String[] tableCardPNG) {
        this.tableCardPNG = tableCardPNG;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public boolean[] getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean[] isActive) {
        this.isActive = isActive;
    }

    public int[] getPlayerBets() {
        return playerBets;
    }

    public void setPlayerBets(int[] playerBets) {
        this.playerBets = playerBets;
    }

    public String[] getDeck() {
        return deck;
    }

    public void setDeck(String[] deck) {
        this.deck = deck;
    }

    public boolean isInteract() {
        return isInteract;
    }
}
