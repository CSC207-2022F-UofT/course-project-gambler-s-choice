package game_use_case;

/**
 * This represents the presenter in clean architecture. This contains the signatures of the methods
 * that will be used to return the view object.
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
