package game_use_case;

import screens.GameFail;

/**
 * This class formats the response object and returns the corresponding response model
 * Implements FoldPresenter interface
 */
public class FoldResponseFormatter implements FoldPresenter{
    /**
     * Returns a new ResponseModel with the updated Game data
     * @param outputData the given ResponseModel
     * @return the given response model
     */
    @Override
    public ResponseModel prepareSuccessView(ResponseModel outputData) {
        return outputData;
    }

    /**
     * Throws a game fail error
     * @param error the error message
     * @return a game fail error with the error message
     */
    @Override
    public ResponseModel prepareFailView(String error) {
        throw new GameFail(error);
    }
}
