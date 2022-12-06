package login_use_case;

import entities.User;
import java.io.FileNotFoundException;

public class LoginInteractor implements UserInputBoundary{

    UserDataAccessInterface dataAccessInterface;
    User user;
    UserOutputBoundary outputBoundary;

    public LoginInteractor(UserDataAccessInterface dataAccessInterface, User user, UserOutputBoundary outputBoundary){
        this.user = user;
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public UserResponseModel create(UserRequestModel requestModel) throws FileNotFoundException {

        boolean userNameBool = dataAccessInterface.getUsernameBool();
        boolean passwordBool = dataAccessInterface.getPasswordBool();
        boolean typeBool = dataAccessInterface.getTypeBool();

        dataAccessInterface.createFile();

        User newUser = new User(requestModel.getUsername(), requestModel.getPassword(), requestModel.getType());

        dataAccessInterface.changeBoolean(newUser);

        if (userNameBool && passwordBool && typeBool){
            return outputBoundary.prepareSuccessView();
        } else if (userNameBool && passwordBool){
            return outputBoundary.prepareWrongTypeView();
        } else {
            dataAccessInterface.createNewAccount(newUser);
            return outputBoundary.prepareAccountCreatedView();
        }
    }
}
