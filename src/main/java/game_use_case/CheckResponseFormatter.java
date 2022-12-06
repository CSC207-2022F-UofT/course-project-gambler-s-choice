package game_use_case;

import screens.GameFail;

public class CheckResponseFormatter implements CheckPresenter {
    @Override
    public CheckResponseModel prepareSuccessView(CheckResponseModel response) {
        return response;
    }

    @Override
    public CheckResponseModel prepareFailView(String error) {
        throw new GameFail(error);
    }
}
