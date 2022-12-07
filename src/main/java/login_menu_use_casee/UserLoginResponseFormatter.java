package login_menu_use_casee;

import screens.LoginFail;

public class UserLoginResponseFormatter implements UserLoginPresenter{

    @Override
    public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
        return new UserLoginResponseModel(user.getUser(), user.getPassword(), user.getType(), user.getBalance(), true);
    }

    @Override
    public UserLoginResponseModel prepareFailView(String error) {
        throw new LoginFail(error);
    }
}
