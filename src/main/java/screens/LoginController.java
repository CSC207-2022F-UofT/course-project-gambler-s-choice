package screens;

import login_menu_use_case.UserLoginInputBoundary;
import login_menu_use_case.UserLoginRequestModel;
import login_menu_use_case.UserLoginResponseModel;

/**
 * Controller used by the Login use case
 */
public class LoginController{

    final UserLoginInputBoundary userInput;

    /**
     * Constructor of the Controller which initializes the necessary gateway
     * @param accountGateway The input boundary for Login Screen
     */
    public LoginController(UserLoginInputBoundary accountGateway){
        this.userInput = accountGateway;
    }

    /**
     * Creates the required response for the action depending on the input
     * @param user The username of the user
     * @param password The password of the user
     * @return The response model that returns the necessary response
     */
    UserLoginResponseModel create(String user, String password){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(
                user, password);

        return userInput.create(requestModel);
    }
}
