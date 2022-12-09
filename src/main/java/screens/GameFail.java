package screens;

/**
 * Specific error for the game use cases
 */
public class GameFail extends RuntimeException{

    /**
     * Called with the error if the game use cases returns fail
     * @param error the response for the error
     */
    public GameFail(String error) {
        super(error);
    }
}
