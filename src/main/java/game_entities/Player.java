package game_entities;

import game_entities.Card;

/**
 *
 */
public class Player implements PlayerInterface {
    private int balance;
    private Card[] cards;
    private boolean fold;
    private boolean allIn;


    public Player(int balance){
        this.balance = balance;
        this.cards = new Card[2];
        this.fold = false;
    }

    public Player(Card card1, Card card2){
        this.cards = new Card[2];
        cards[0] = card1;
        cards[1] = card2;
    }

    public String makeDecision(int currentCall, int haveCalled){
        String decision = "something";

        //TODO: add a action listener of the button and alt the value of <decision>
        // "F" for fold, "C" for call, "R300" for raise to 300 or "K" for check

        // In the program, "check" is considered as raising to $0

        //TODO: if <currentCall> is not 0, then Check button is disabled

        if (decision.equals("F")){
            this.fold = true;
        } else if(decision.charAt(0) == 'C'){
            while(currentCall - haveCalled > this.balance){
                //TODO: raise insuffcient balance message and alt the <decision> again
            }
            balance -= currentCall;
        } else if (decision.charAt(0) == 'R'){
            int raise = Integer.parseInt(decision.substring(1));
            while (raise - haveCalled > this.balance){
                //TODO: raise insufficient balance message and alt the <decision> again
            }
            while (raise < 2 * currentCall){
                //TODO: raise not doubling current call message and alt the <decision> again
            }
        }
        return decision;
    }

    public void addMoney(int amount){
        this.balance += amount;
    }

    public boolean getFold(){
        return this.fold;
    }

    public boolean getAllIn(){
        return this.allIn;
    }
    public int betSmallBlind(){
        //TODO: create a pop-up for input of small blind
        balance -= 1;
        return 1;
    }

    public int betBigBlind(int sb){
        //TODO: create a pop-up for input of big blind

        //TODO: raise not higher than small blind message and alt the <call> again
        balance -= 2;
        return 2;
    }

    /**
     * Bets a certain amount of money
     * Removes the bet amount from player's balance
     *
     * @param amount amount the player has bet
     */
    public void bet(int amount) {
        this.balance -= amount;
    }

    public void receiveCard(Card card){
        if (cards[0] == null){
            cards[0] = card;
        } else{
            cards[1] = card;
        }
    }

    public Card[] getCards(){
        return this.cards;
    }

    public int getBalance() {
        return balance;
    }

    /**
     * Method to distribute new cards to players
     *
     * @param card1 first card
     * @param card2 second card
     */
    public void setCards(Card card1, Card card2) {
        this.cards[0] = card1;
        this.cards[1] = card2;
    }
}
