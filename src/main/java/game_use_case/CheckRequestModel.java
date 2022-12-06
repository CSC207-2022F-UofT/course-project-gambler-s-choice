package game_use_case;

public class CheckRequestModel {
    private int player;
    private String move;

    public CheckRequestModel(int player, String move) {
        this.player = player;
        this.move = move;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}
