package login_use_case;

public interface UserOutputBoundary {

    UserResponseModel prepareSuccessView();

    UserResponseModel prepareWrongTypeView();

    UserResponseModel prepareWrongPasswordView();

    UserResponseModel prepareAccountCreatedView();
}
