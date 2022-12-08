package login_use_case_tests;

import login_menu_entities.UserFactory;
import login_menu_entities.UserInterfaceFactory;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import register_menu_use_case.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for possible outcomes from register use case
 */
public class RegisterUseCaseTest {

    /**
     * Testing for a successful new user creation
     */
    @Test
    void registerSuccess(){
        String usersfile = "src/test/java/login_use_case_tests/loginTest.txt";
        UserRegisterDSGateway gateway;
        try {
            gateway = new RegisterFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserInterfaceFactory userFactory = new UserFactory();
        UserRegisterPresenter presenter = new UserRegisterPresenter() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
                assertEquals("bob", user.getUser());
                assertEquals(100, user.getBalance());
                assertEquals("user", user.getType());
                assertTrue(user.isLoggedIn());
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                fail(error);
                return null;
            }
        };
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(gateway, presenter, userFactory);

        UserRegisterRequestModel inputdata = new UserRegisterRequestModel("bob", "password1", "password1");

        interactor.create(inputdata);
    }

    /**
     * Testing for a failed new user creation due to non-matching passwords
     */
    @Test
    void registerPasswordFail(){
        String usersfile = "src/test/java/login_use_case_tests/loginTest.txt";
        UserRegisterDSGateway gateway;
        try {
            gateway = new RegisterFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserInterfaceFactory userFactory = new UserFactory();
        UserRegisterPresenter presenter = new UserRegisterPresenter() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
                fail();
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                assertEquals("Passwords do not match", error);
                return null;
            }
        };
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(gateway, presenter, userFactory);

        UserRegisterRequestModel inputdata = new UserRegisterRequestModel("jim", "password1", "asdasdas");

        interactor.create(inputdata);
    }

    /**
     * Testing for a failed new user creation due to existing username
     */
    @Test
    void registerUserFail(){
        String usersfile = "src/test/java/login_use_case_tests/loginTest.txt";
        UserRegisterDSGateway gateway;
        try {
            gateway = new RegisterFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserInterfaceFactory userFactory = new UserFactory();
        UserRegisterPresenter presenter = new UserRegisterPresenter() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
                fail();
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                assertEquals("Username already taken", error);
                return null;
            }
        };
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(gateway, presenter, userFactory);

        UserRegisterRequestModel inputdata = new UserRegisterRequestModel("username", "password1", "password1");

        interactor.create(inputdata);
    }

    /**
     * Testing for a failed new user creation due to the username field being unfilled
     */
    @Test
    void registerEmptyUserFail(){
        String usersfile = "src/test/java/login_use_case_tests/loginTest.txt";
        UserRegisterDSGateway gateway;
        try {
            gateway = new RegisterFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserInterfaceFactory userFactory = new UserFactory();
        UserRegisterPresenter presenter = new UserRegisterPresenter() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
                fail();
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                assertEquals("Please enter a username and password", error);
                return null;
            }
        };
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(gateway, presenter, userFactory);

        UserRegisterRequestModel inputdata = new UserRegisterRequestModel("", "password1", "password1");

        interactor.create(inputdata);
    }

    /**
     * Testing for a failed new user creation due to empty password field
     */
    @Test
    void registerEmptyPassFail(){
        String usersfile = "src/test/java/login_use_case_tests/loginTest.txt";
        UserRegisterDSGateway gateway;
        try {
            gateway = new RegisterFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserInterfaceFactory userFactory = new UserFactory();
        UserRegisterPresenter presenter = new UserRegisterPresenter() {
            @Override
            public UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user) {
                fail();
                return null;
            }

            @Override
            public UserRegisterResponseModel prepareFailView(String error) {
                assertEquals("Please enter a username and password", error);
                return null;
            }
        };
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(gateway, presenter, userFactory);

        UserRegisterRequestModel inputdata = new UserRegisterRequestModel("jill", "", "password1");

        interactor.create(inputdata);
    }
}
