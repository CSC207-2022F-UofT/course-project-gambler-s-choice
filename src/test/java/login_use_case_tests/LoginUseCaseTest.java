package login_use_case_tests;

import login_menu_use_casee.*;
import login_menu_entities.UserFactory;
import login_menu_entities.UserInterfaceFactory;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the possible outcomes in the login use case
 */
public class LoginUseCaseTest {

    /**
     * Testing for a successful login where the user's data is properly loaded
     */
    @Test
    void loginSuccess(){
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

    /**
     * Testing for a failed login due to the username not existing
     */
    @Test
    void loginUserFail(){
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
                fail();
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                assertEquals("User not found", error);
                return null;
            }
        };

        UserInterfaceFactory userFactory = new UserFactory();
        UserLoginInputBoundary interactor = new UserLoginInteractor(user, presenter, userFactory);

        UserLoginRequestModel inputdata = new UserLoginRequestModel("billy", "password");

        interactor.create(inputdata);
    }

    /**
     * Testing for a failed login due to password not matching password in database
     */
    @Test
    void loginPassFail(){
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
                fail();
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                assertEquals("Password does not match", error);
                return null;
            }
        };

        UserInterfaceFactory userFactory = new UserFactory();
        UserLoginInputBoundary interactor = new UserLoginInteractor(user, presenter, userFactory);

        UserLoginRequestModel inputdata = new UserLoginRequestModel("username", "pssword");

        interactor.create(inputdata);
    }

    /**
     * Testing for a failed login due to user field being empty
     */
    @Test
    void loginEmptyUserFail(){
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
                fail();
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                assertEquals("Please enter a username and password", error);
                return null;
            }
        };

        UserInterfaceFactory userFactory = new UserFactory();
        UserLoginInputBoundary interactor = new UserLoginInteractor(user, presenter, userFactory);

        UserLoginRequestModel inputdata = new UserLoginRequestModel("", "pssword");

        interactor.create(inputdata);
    }

    /**
     * Testing for a failed login due to password field being empty
     */
    @Test
    void loginEmptyPassFail(){
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
                fail();
                return null;
            }

            @Override
            public UserLoginResponseModel prepareFailView(String error) {
                assertEquals("Please enter a username and password", error);
                return null;
            }
        };

        UserInterfaceFactory userFactory = new UserFactory();
        UserLoginInputBoundary interactor = new UserLoginInteractor(user, presenter, userFactory);

        UserLoginRequestModel inputdata = new UserLoginRequestModel("username", "");

        interactor.create(inputdata);
    }
}