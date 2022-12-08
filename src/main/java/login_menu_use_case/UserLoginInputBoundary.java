package login_menu_use_case;

/**
 * The outline of the input boundary which will create a response from a request
 */
public interface UserLoginInputBoundary {
    /**
     * Creates a Responsemodel corresponding to the Requestmodel
     * @param loginRequestModel The inputs of the use case that has the values needed to create a user entity
     * @return The return of the use case that has the required values of the user entity
     */
    UserLoginResponseModel create(UserLoginRequestModel loginRequestModel);
}
