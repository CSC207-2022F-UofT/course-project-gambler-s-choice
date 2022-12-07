package login_menu_use_casee;

public interface UserLoginPresenter {
    UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user);

    UserLoginResponseModel prepareFailView(String error);
}
