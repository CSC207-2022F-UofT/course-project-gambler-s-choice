package screens;

import register_menu_use_case.UserRegisterInputBoundary;
import register_menu_use_case.UserRegisterRequestModel;
import register_menu_use_case.UserRegisterResponseModel;

public class RegisterController {

    final UserRegisterInputBoundary userInput;

    public RegisterController(UserRegisterInputBoundary accountGateway){
        this.userInput = accountGateway;
    }


    UserRegisterResponseModel create(String user, String password, String password2){
        UserRegisterRequestModel requestModel = new UserRegisterRequestModel(user, password, password2);

        return userInput.create(requestModel);
    }
}
