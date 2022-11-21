package screens;
import menu_use_case.UserEditBalanceInputBoundary;
import menu_use_case.UserEditBalanceModel;
// Interface adapters layer
public class UserEditBalanceController {

    final UserEditBalanceInputBoundary editInput;

    public UserEditBalanceController(UserEditBalanceInputBoundary editGateway){
        this.editInput = editGateway;
    }

    UserEditBalanceModel create(String user, int balance){
        UserEditBalanceModel balanceModel = new UserEditBalanceModel(user, balance);

        return editInput.create(balanceModel);
    }


}
