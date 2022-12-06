package screens;

public class GameFail extends RuntimeException{
    public GameFail(String error) {
        super(error);
    }
}
