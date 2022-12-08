package register_menu_use_case;

import login_menu_entities.UserInterface;
import login_menu_entities.UserInterfaceFactory;

public class UserRegisterInteractor implements UserRegisterInputBoundary{

    final UserRegisterDSGateway userDSGateway;
    final UserRegisterPresenter userPresenter;
    final UserInterfaceFactory userFactory;

    public UserRegisterInteractor(UserRegisterDSGateway userRegisterDSGateway, UserRegisterPresenter userRegisterPresenter, UserInterfaceFactory userFactory){
        this.userDSGateway = userRegisterDSGateway;
        this.userPresenter = userRegisterPresenter;
        this.userFactory = userFactory;
    }

    /**
     * Registers the user into the system
     * If the username and the password input are empty, the system returns "Please enter a username and password"
     * If the input username already exists in the database, the system returns "Username already taken."
     * If the second password field is empty, the system returns "Please confirm your password"
     * If the first input password and second input password are empty, the system returns "Passwords do not match"
     * Otherwise, an accountResponseModel is created.
     * @param RegisterRequestModel the username and password input
     * @return a UserRegisterResponseModel
     */
    @Override
    public UserRegisterResponseModel create(UserRegisterRequestModel RegisterRequestModel) {
        if (RegisterRequestModel.getUser().isEmpty() || RegisterRequestModel.getPassword().isEmpty()){
            return userPresenter.prepareFailView("Please enter a username and password");
        } else if (!(userDSGateway.existsByName(RegisterRequestModel.getUser()))){
            return userPresenter.prepareFailView("Username already taken");
        } else if (RegisterRequestModel.getPassword2().isEmpty()){
            return userPresenter.prepareFailView("Please confirm your password");
        } else if (!(userDSGateway.matchingPass(RegisterRequestModel.getPassword(), RegisterRequestModel.getPassword2()))) {
            return userPresenter.prepareFailView("Passwords do not match");
        }

        String username = RegisterRequestModel.getUser();
        String password = RegisterRequestModel.getPassword();
        UserInterface user = userFactory.create(username, password, "user", -1);

        userDSGateway.save(RegisterRequestModel);
        UserRegisterResponseModel accountResponseModel = new UserRegisterResponseModel(user.getName(), user.getPassword(), user.getType(), user.getBalance(), true);
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
