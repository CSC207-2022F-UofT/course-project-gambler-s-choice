package game_use_case;

public interface BetPresenter {
    ResponseModel prepareSuccessView(ResponseModel outputData);
    ResponseModel prepareFailView(String error);
}
