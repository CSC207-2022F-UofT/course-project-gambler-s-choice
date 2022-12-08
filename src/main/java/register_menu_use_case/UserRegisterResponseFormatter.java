package register_menu_use_case;

import screens.LoginFail;

public class UserRegisterResponseFormatter implements UserRegisterPresenter{

    /**
     * Returns a new UserRegisterResponseModel with the given user's information and the loggedin status set to true
     * @param user the given UserLoginResponseModel
     * @return a new UserRegisterResponseModel with the given user's information and the loggedin status set to true
     */
    @Override
    public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
        return new UserRegisterResponseModel(user.getUser(), user.getPassword(), user.getType(), user.getBalance(), true);
    }

    /**
     * Throws a login fail error
     * @param error the given error message
     * @return a login fail error with the given error message
     */
    @Override
    public UserRegisterResponseModel prepareFailView(String error) {
        throw new LoginFail(error);
    }
}
