package game_use_case;

import screens.GameFail;

/**
 * This class formats the output from the Leave use case interactor to be used by the GameScreen class.
 * Implements the LeavePresenter interface.
 */
public class LeaveResponseFormatter implements LeavePresenter{
    /**
     * Returns a boolean based on the one given
     * @param exit the given boolean variable
     * @return the given response model
     */
    @Override
    public boolean exitGame(boolean exit) {
        return exit;
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
