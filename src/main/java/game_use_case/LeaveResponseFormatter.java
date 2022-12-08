package game_use_case;

import screens.GameFail;

public class LeaveResponseFormatter implements LeavePresenter{
    @Override
    public boolean exitGame(boolean exit) {
        return exit;
    }

    @Override
    public ResponseModel prepareFailView(String error) {
        throw new GameFail(error);
    }
}
