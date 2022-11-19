// TODO Delete this class
public class Player {

    public boolean fold;
    public boolean allIn;

    public int num;

    public static int number = 0;

    public int money;

    public Player() {
        this.fold = false;
        this.allIn = false;
        number++;
        this.num = number;
        this.money = 0;
    }

    public void addMoney(int amount) {money += amount;}

    public boolean getFold() { return this.fold; }

    public boolean getAllIn() { return this.allIn; }

    @Override
    public String toString() {
        return Integer.toString(this.num);
    }
}
