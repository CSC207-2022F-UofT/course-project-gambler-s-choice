package game_use_case;

import screens.GameFail;

public class NewGameResponseFormatter implements NewGamePresenter{
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
