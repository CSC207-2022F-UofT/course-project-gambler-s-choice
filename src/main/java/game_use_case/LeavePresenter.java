package game_use_case;

/**
 *
 */
public interface LeavePresenter {
    /**
     * This method will return a response model given the current response model
     * @param exit determines when to exit
     * @return the new response model
     */
    boolean exitGame(boolean exit);

    /**
     * This method will throw an error given the error message
     * @param error the error message
     * @return should throw an error
     */
    ResponseModel prepareFailView(String error);
}
