package login_use_case_tests;

import login_menu_use_casee.*;
import login_menu_entities.UserFactory;
import login_menu_entities.UserInterfaceFactory;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginUseCaseTest {

    @Test
    void create(){
        String usersfile = "src/test/java/login_use_case_tests/loginTest.txt";
        UserLoginDSGateway user;
        try {
            user = new LoginFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserLoginPresenter presenter = new UserLoginPresenter() {
            @Override
            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
                assertEquals("username", user.getUser());
                assertEquals(100, user.getBalance());
                assertEquals("user", user.getType());
                assertTrue(user.isLoggedIn());
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                fail(error);
                return null;
            }
        };

        UserInterfaceFactory userFactory = new UserFactory();
        UserLoginInputBoundary interactor = new UserLoginInteractor(user, presenter, userFactory);

        UserLoginRequestModel inputdata = new UserLoginRequestModel("username", "password");

        interactor.create(inputdata);
    }

}