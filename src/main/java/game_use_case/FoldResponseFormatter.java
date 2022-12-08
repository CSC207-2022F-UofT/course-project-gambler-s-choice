package game_use_case;

import screens.GameFail;

/**
 * Implements the FoldPresenter interface. Is how the game state is communicated between the GameScreen and the Game.
 * Use case layer
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
