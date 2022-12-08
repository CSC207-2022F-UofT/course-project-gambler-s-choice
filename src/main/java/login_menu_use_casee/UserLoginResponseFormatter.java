package login_menu_use_casee;

import screens.LoginFail;

public class UserLoginResponseFormatter implements UserLoginPresenter{

    /**
     * Returns a new UserLoginResponseModel with the given user's information and the loggedin status set to true
     * @param user the given UserLoginResponseModel
     * @return a new UserLoginResponseModel with the given user's information and the loggedin status set to true
     */
    @Override
    public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
        return new UserLoginResponseModel(user.getUser(), user.getPassword(), user.getType(), user.getBalance(), true);
    }

    /**
     * Throws a login fail error
     * @param error the given error message
     * @return a login fail error with the given error message
     */
    @Override
    public UserLoginResponseModel prepareFailView(String error) {
        throw new LoginFail(error);
    }
}
