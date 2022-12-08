package game_use_case;

public interface CheckPresenter {
    ResponseModel prepareSuccessView(ResponseModel outputData);
    ResponseModel prepareFailView(String error);
}
