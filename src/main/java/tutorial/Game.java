package tutorial;
import javax.swing.*;

//this is an entity
public class Game {
    private int numPlayers;
    public Game(int n) {
        this.numPlayers = n;
        GameDisplay g = new GameDisplay(new JPanel());
        GameController gCont = new GameController(g);
    }

    public boolean setupGame() {
        return true;
    }

    public static String printInput(String command) {
        System.out.println(command);
        return command;
    }

    public static void main(String[]args) {
        Game g = new Game(1);
    }
}
