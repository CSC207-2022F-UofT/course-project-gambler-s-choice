package game_use_case;

import screens.GameFail;

public class CheckResponseFormatter implements CheckPresenter {
    @Override
    public ResponseModel prepareSuccessView(ResponseModel response) {
        return response;
    }

    @Override
    public ResponseModel prepareFailView(String error) {
        throw new GameFail(error);
    }
}