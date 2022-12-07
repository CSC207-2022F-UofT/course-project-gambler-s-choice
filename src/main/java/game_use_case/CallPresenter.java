package game_use_case;

public interface CallPresenter {
    ResponseModel prepareSuccessView(ResponseModel outputData);
    ResponseModel prepareFailView(String error);
}
