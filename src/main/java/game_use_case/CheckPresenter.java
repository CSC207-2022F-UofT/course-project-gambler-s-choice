package game_use_case;

public interface CheckPresenter {
    CheckResponseModel prepareSuccessView(CheckResponseModel outputData);
    CheckResponseModel prepareFailView(String error);
}
