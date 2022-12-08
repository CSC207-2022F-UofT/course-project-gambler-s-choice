package menu_use_case_tests;

import menu_use_case.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MenuUseCaseTest {
    @Test
    void create(){
        String testFile = "src/test/java/menu_use_case_tests/MenuTestFile";
        MenuDSGateway userMenu;
        try {
            userMenu = new MenuFileChecker(testFile);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        MenuPresenter menu = new MenuPresenter() {
            @Override
            public MenuResponseModel prepareSuccessView(MenuResponseModel user) {
                assertEquals(100, userMenu.getBalance("test"));
                assertEquals(100, userMenu.getBalance("test"));
                return null;
            }

            @Override
            public MenuResponseModel prepareFailView(String error) {
                fail(error);
                return null;
            }
        };
        MenuInputBoundary boundary = new MenuInteractor(userMenu, menu);
        MenuRequestModel requestModel = new MenuRequestModel("test", "test", false);
        boundary.create(requestModel);
    }
}

