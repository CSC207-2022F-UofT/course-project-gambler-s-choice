package register_menu_use_case;

import login_menu_use_casee.UserLoginResponseModel;
import screens.LoginFail;

public class UserRegisterResponseFormatter implements UserRegisterPresenter{

    @Override
    public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
        return new UserRegisterResponseModel(user.getUser(), user.getPassword(), user.getType(), user.getBalance(), true);
    }

    @Override
    public UserRegisterResponseModel prepareFailView(String error) {
        throw new LoginFail(error);
    }
}
