//TEMPORARY FILE WILL BE OVERWRITTEN BY ACTUAL PLAYER CLASS
//TODO Replace
public class Player {
    private Card[] cards = new Card[2];

    public Player(Card card1, Card card2){
        cards[0] = card1;
        cards[1] = card2;
    }
    public Card[] getCards(){
        return cards;
    }
}
