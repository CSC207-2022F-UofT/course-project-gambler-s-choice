package login_use_case;


import java.io.FileNotFoundException;

public interface UserInputBoundary {
    UserResponseModel create(UserRequestModel requestModel) throws FileNotFoundException;
}
