public class Game {

    private Player[] players;
    private Card[] boardCards = new Card[3];

    public Game(Player[] players){
        this.players = players;
        Card card1 = new Card("10", "S");
        Card card2 = new Card("J", "S");
        Card card3 = new Card("K", "S");
        this.boardCards[0] = card1;
        this.boardCards[1] = card2;
        this.boardCards[2] = card3;

    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getCurrPlayer(){
        return players[0];
    }

    public Card[] getBoardCards(){
        return boardCards;
    }
}
