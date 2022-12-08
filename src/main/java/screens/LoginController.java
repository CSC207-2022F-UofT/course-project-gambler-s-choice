package screens;

import login_menu_use_case.UserLoginInputBoundary;
import login_menu_use_case.UserLoginRequestModel;
import login_menu_use_case.UserLoginResponseModel;

public class LoginController{

    final UserLoginInputBoundary userInput;

    public LoginController(UserLoginInputBoundary accountGateway){
        this.userInput = accountGateway;
    }

    UserLoginResponseModel create(String user, String password){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(
                user, password);

        return userInput.create(requestModel);
    }
}
