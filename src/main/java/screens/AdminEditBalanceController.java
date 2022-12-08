package screens;
import admin_menu_use_case.AdminEditBalanceInputBoundary;
import admin_menu_use_case.AdminEditBalanceModel;
import admin_menu_use_case.AdminEditResponseModel;
// Interface adapters layer
public class AdminEditBalanceController {
    /**
     * This creates a UserEditBalanceModel
     */
    final AdminEditBalanceInputBoundary editInput;

    public AdminEditBalanceController(AdminEditBalanceInputBoundary editGateway){
        this.editInput = editGateway;
    }

    AdminEditResponseModel create(String user, String balance, String input, boolean rulesVisible){
        AdminEditBalanceModel balanceModel = new AdminEditBalanceModel(user, balance, input, rulesVisible);

        return editInput.create(balanceModel);
    }


}