package login_menu_use_case;

import login_menu_entities.UserInterface;
import login_menu_entities.UserInterfaceFactory;

/**
 * Implementation of an inputboundary which takes the inputs and performs the necessary reaction in the presenter
 */
public class UserLoginInteractor implements UserLoginInputBoundary {

    final UserLoginDSGateway userDSGateway;
    final UserLoginPresenter userPresenter;
    final UserInterfaceFactory userFactory;

    /**
     * Constructor of the interactor which properly assigns the necessary gateway, presenter and factory
     * @param userLoginDSGateway The gateway needed to interact with the database
     * @param userLoginPresenter The presenter needed to pass values to update the view
     * @param userFactory The factory necessary to create the necessary entities
     */
    public UserLoginInteractor(UserLoginDSGateway userLoginDSGateway, UserLoginPresenter userLoginPresenter, UserInterfaceFactory userFactory){
        this.userDSGateway = userLoginDSGateway;
        this.userPresenter = userLoginPresenter;
        this.userFactory = userFactory;
    }

    /**
     * Logs the user into the system.
     * If the username and the password input are empty, the system returns "Please enter a username and password"
     * If the input username does not exist in the database, the system returns "User not found."
     * If the input username exists in the database but the password does not match with the input password, the system
     * returns "Password does not match".
     * Otherwise, an accountResponseModel is created
     * @param loginRequestModel the username and password input
     * @return a UserLoginResponseModel
     */
    @Override
    public UserLoginResponseModel create(UserLoginRequestModel loginRequestModel) {
        if (loginRequestModel.getUser().isEmpty() || loginRequestModel.getPassword().isEmpty()){
            return userPresenter.prepareFailView("Please enter a username and password");
        } else if(!(userDSGateway.existsByName(loginRequestModel.getUser()))){
            return userPresenter.prepareFailView("User not found");
        } else if (!(userDSGateway.matchingPass(loginRequestModel.getUser(), loginRequestModel.getPassword()))) {
            return userPresenter.prepareFailView("Password does not match");
        }
        String username = loginRequestModel.getUser();
        String password = loginRequestModel.getPassword();
        UserInterface user = userFactory.create(username, password, userDSGateway.getAccountInfo(username, password)[0],
                Integer.parseInt(userDSGateway.getAccountInfo(username, password)[1]));

        UserLoginResponseModel accountResponseModel = new UserLoginResponseModel(user.getName(), user.getPassword(), user.getType(), user.getBalance(), true);
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
