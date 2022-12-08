package admin_use_case_tests;

import admin_menu_use_case.*;

import java.io.IOException;

import login_menu_use_casee.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AdminEditUseCaseTest {
    @Test
    void create(){
        String usersfile = "src/test/java/login_use_case_tests/loginTest.txt";
        AdminEditGateway admin;
        try {
            admin = new AdminFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        AdminEditPresenter presenter = new AdminEditPresenter() {
            @Override
            public AdminEditResponseModel prepareSuccessView(AdminEditResponseModel user) {

                assertEquals(100, admin.getBalance("123"));
                return null;
            }

            @Override
            public AdminEditResponseModel prepareFailView(String error) {
                fail(error);
                return null;
            }
        };


        AdminEditBalanceInputBoundary interactor = new AdminEditInteractor(admin, presenter);

        AdminEditBalanceModel inputdata = new AdminEditBalanceModel("123", "123", "123", false);

        interactor.create(inputdata);
    }

}

