package game_use_case;

import screens.GameFail;

public class FoldResponseFormatter implements FoldPresenter{
    @Override
    public ResponseModel prepareSuccessView(ResponseModel outputData) {
        return outputData;
    }

    @Override
    public ResponseModel prepareFailView(String error) {
        throw new GameFail(error);
    }
}
