import java.util.*;

public class Game {

    private String[] river;

    private ArrayList<Player> players;

    public void addPlayer(Player p) {
        players.add(p);
    }

    //debugging method
    public void setRiver(String[] arr) {
        this.river = arr;
    }

    public int findWinner() {
        int largestScore = 0;
        int winningPlayerId = -1;
        int scoreVal = 0;

        Set<Character> riverSuit = new HashSet<>();
        int[] riverRank = new int[5];
        for (int x = 0; x < this.river.length; x++) {
            riverSuit.add(this.river[x].charAt(0));
            riverRank[x] = this.river[x].charAt(1) - '0';
        }
        Arrays.sort(riverRank);

        for (Player p : players) {
            scoreVal = calculateHand(p.getPlayerHand(), riverSuit, riverRank);

            if (scoreVal > largestScore) {
                winningPlayerId = p.getId();
            }
        }
        return winningPlayerId;
    }

    public int calculateHand(String[] hand, Set riverSuit, int[] riverRank) {



        return 0;
    }

    public static void main(String[]args) {
        GameScreen g = new GameScreen(1);
    }
}
