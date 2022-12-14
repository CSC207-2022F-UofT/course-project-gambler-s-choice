package tutorial;

public class Player {
    private int id;
    private double playerMoney;
    private String[] hand;

    public Player(int id) {
        this.id = id;
    }

    public Player(int id, String[] hand) {
        this.id = id;
        this.hand = hand;
    }

    public int getId() {
        return this.id;
    }

    public void addMoney(double money){
        this.playerMoney += money;
    }

    public String[] getPlayerHand() {
        return this.hand;
    }

    public void setPlayerHand(String[] arr) {
        this.hand = arr;
    }
}
