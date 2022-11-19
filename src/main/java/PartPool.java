import java.util.ArrayList;
import java.util.List;

public class PartPool {
    private int amount = 0;
    private ArrayList<Player> playersEntitled;

    public PartPool(Player[] players) {
        this.playersEntitled = new ArrayList<>();
        this.playersEntitled.addAll(List.of(players));
    }

    public PartPool(ArrayList<Player> players) {
        this.playersEntitled = players;
    }

    public void removePlayer(Player player) {
        this.playersEntitled.remove(player);
    }

    public int getAmount() {
        return this.amount;
    }

    public ArrayList<Player> getPlayersEntitled() {
        return this.playersEntitled;
    }

    public void addMoney(int amount) {
        this.amount += amount;
    }

}
