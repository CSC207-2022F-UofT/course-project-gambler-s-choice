package admin_use_case_tests;

import admin_menu_use_case.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.AdminEditBalanceController;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AdminEditInteractorTest {
    /**
     * This test tests each of the methods of AdminFileChecker
     */
    @Test
    void create(){
        String testFile = "src/test/java/admin_use_case_tests/AdminEditInteractorTest";
        AdminEditGateway admin;
        try {
            admin = new AdminFileChecker(testFile);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        admin.editByName("123", 0);

        assert !admin.sufficientBalance("123");

        assert !admin.existsByName("testfail");

        admin.editByName("123", 100);

        assert admin.sufficientBalance("123");

        assert !admin.validBalance("testfail");

        assert admin.getBalance("123") == 100;
    }
}
