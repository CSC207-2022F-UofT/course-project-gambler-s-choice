package login_menu_use_casee;

import login_menu_entities.UserInterface;
import login_menu_entities.UserInterfaceFactory;

public class UserLoginInteractor implements UserLoginInputBoundary {

    final UserLoginDSGateway userDSGateway;
    final UserLoginPresenter userPresenter;
    final UserInterfaceFactory userFactory;

    public UserLoginInteractor(UserLoginDSGateway userLoginDSGateway, UserLoginPresenter userLoginPresenter, UserInterfaceFactory userFactory){
        this.userDSGateway = userLoginDSGateway;
        this.userPresenter = userLoginPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public UserLoginResponseModel create(UserLoginRequestModel loginRequestModel) {
        if (loginRequestModel.getUser().isEmpty() || loginRequestModel.getPassword().isEmpty()){
            return userPresenter.prepareFailView("Please enter a username and password");
        } else if(!(userDSGateway.existsByName(loginRequestModel.getUser()))){
            return userPresenter.prepareFailView("User not found");
        } else if (!(userDSGateway.matchingPass(loginRequestModel.getPassword()))) {
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
