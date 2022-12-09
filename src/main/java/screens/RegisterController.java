package screens;

import register_menu_use_case.UserRegisterInputBoundary;
import register_menu_use_case.UserRegisterRequestModel;
import register_menu_use_case.UserRegisterResponseModel;

/**
 * Controller for the resgister use case
 */
public class RegisterController {

    final UserRegisterInputBoundary userInput;

    /**
     * Constructor of the controller which initializes the necessary gateway
     * @param accountGateway the input boundary for the bet
     */
    public RegisterController(UserRegisterInputBoundary accountGateway){
        this.userInput = accountGateway;
    }

    /**
     * Creates the required response for the action depending on the input
     * @param user The username of the user
     * @param password The password of the user
     * @param password2 The repeated password of the user
     * @return The response model that returns the necessary response
     */
    UserRegisterResponseModel create(String user, String password, String password2){
        UserRegisterRequestModel requestModel = new UserRegisterRequestModel(user, password, password2);

        return userInput.create(requestModel);
    }
}
