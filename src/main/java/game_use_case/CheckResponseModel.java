package game_use_case;

public class CheckResponseModel {
    String card1;
    String card2;
    String[] table;

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public String[] getTable() {
        return table;
    }

    public void setTable(String[] table) {
        this.table = table;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public int getBetTotal() {
        return betTotal;
    }

    public void setBetTotal(int betTotal) {
        this.betTotal = betTotal;
    }

    public CheckResponseModel(String card1, String card2, String[] table, int currentBet, int betTotal) {
        this.card1 = card1;
        this.card2 = card2;
        this.table = table;
        this.currentBet = currentBet;
        this.betTotal = betTotal;
    }

    int currentBet;
    int betTotal;


}
