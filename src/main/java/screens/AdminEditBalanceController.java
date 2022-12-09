package screens;
import admin_menu_use_case.AdminEditBalanceInputBoundary;
import admin_menu_use_case.AdminEditBalanceModel;
import admin_menu_use_case.AdminEditResponseModel;

/**
 * Controller used by the AdminMainMenu screen
 */
public class AdminEditBalanceController {

    final AdminEditBalanceInputBoundary editInput;

    /**
     * Constructor of the Controller which initializes the necessary gateway
     * @param editGateway The input boundary for Admin's Main Menu
     */
    public AdminEditBalanceController(AdminEditBalanceInputBoundary editGateway){
        this.editInput = editGateway;
    }

    /**
     * Creates the required response for the action depending on the input
     * @param user The username of the user provided by the login from before
     * @param balance The balance of the user
     * @param input What button they press
     * @param rulesVisible Whether the rules on the menuscreen are visible or not
     * @return The response model that updates the file and returns the necessary response
     */
    AdminEditResponseModel create(String user, String balance, String input, boolean rulesVisible){
        AdminEditBalanceModel balanceModel = new AdminEditBalanceModel(user, balance, input, rulesVisible);

        return editInput.create(balanceModel);
    }


}
