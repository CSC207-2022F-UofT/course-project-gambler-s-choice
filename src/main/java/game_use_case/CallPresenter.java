package game_use_case;

/**
 * This class is a presenter in clean architecture. It is used as input.
 */
public interface CallPresenter {
    /**
     * This method will return a response model given the current response model
     * @param outputData the response model that will be passed into the function
     * @return the new response model
     */
    ResponseModel prepareSuccessView(ResponseModel outputData);
    /**
     * This method will throw an error given the error message
     * @param error the error message
     * @return should throw an error
     */
    ResponseModel prepareFailView(String error);
}
