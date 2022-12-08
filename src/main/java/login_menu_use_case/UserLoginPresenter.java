package login_menu_use_case;

public interface UserLoginPresenter {
    /**
     * Creates a response after a successful input
     * @param user
     * @return
     */
    UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user);

    UserLoginResponseModel prepareFailView(String error);
}
