package screens;

import login_menu_use_casee.UserLoginInputBoundary;
import login_menu_use_casee.UserLoginRequestModel;
import login_menu_use_casee.UserLoginResponseModel;

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
