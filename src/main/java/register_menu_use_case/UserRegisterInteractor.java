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
