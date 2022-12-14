package game_use_case;

import screens.GameFail;

/**
 * This class formats the output from the bet use case interactor to be used by the GameScreen class.
 * Implements the CallPresenter interface.
 */
public class BetResponseFormatter implements BetPresenter{
    /**
     * Returns a new ResponseModel with the updated Game data
     * @param response the given ResponseModel
     * @return the given response model
     */
    @Override
    public ResponseModel prepareSuccessView(ResponseModel response) {
        return response;
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
