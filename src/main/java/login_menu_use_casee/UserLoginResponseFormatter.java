package login_menu_use_casee;

import screens.LoginFail;

public class UserLoginResponseFormatter implements UserLoginPresenter{

    @Override
    public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
        return null;
    }

    @Override
    public UserLoginResponseModel prepareFailView(String error) {
        throw new LoginFail(error);
    }
}
