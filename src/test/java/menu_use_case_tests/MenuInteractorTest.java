package menu_use_case_tests;

import menu_use_case.*;
import admin_menu_use_case.AdminEditGateway;
import admin_menu_use_case.AdminFileChecker;
import menu_use_case.MenuDSGateway;
import org.junit.jupiter.api.Test;

public class MenuInteractorTest {
    @Test
    void create(){
        String testFile = "src/test/java/menu_use_case_tests/MenuTestFile";
        MenuDSGateway user;
        try {
            user = new MenuFileChecker(testFile);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        assert user.sufficientBalance("test");
        assert user.getBalance("test") == 100;

    }
}
