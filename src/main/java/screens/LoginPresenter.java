package screens;

import login_use_case.UserOutputBoundary;
import login_use_case.UserResponseModel;

public class LoginPresenter implements UserOutputBoundary {
    @Override
    public UserResponseModel prepareSuccessView() {
        UserResponseModel responseModel = new UserResponseModel("Welcome Screen");
        return responseModel;
    }

    @Override
    public UserResponseModel prepareWrongTypeView() {
        UserResponseModel responseModel = new UserResponseModel("Incorrect type of user.");
        return responseModel;
    }

    @Override
    public UserResponseModel prepareWrongPasswordView() {
        UserResponseModel responseModel = new UserResponseModel("Incorrect password.");
        return responseModel;
    }

    @Override
    public UserResponseModel prepareAccountCreatedView() {
        UserResponseModel responseModel = new UserResponseModel("New account created.");
        return responseModel;
    }
}
