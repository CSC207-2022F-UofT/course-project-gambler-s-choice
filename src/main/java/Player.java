// TODO Delete this class
public class Player {

    public boolean fold;
    public boolean allIn;

    public int num;

    public static int number = 0;

    public Player() {
        this.fold = false;
        this.allIn = false;
        number++;
        this.num = number;
    }

    public void addMoney(int amount) {}

    public boolean getFold() { return this.fold; }

    public boolean getAllIn() { return this.allIn; }

    @Override
    public String toString() {
        return Integer.toString(this.num);
    }
}
