package menu_use_case;

import login_menu_use_casee.UserLoginResponseModel;
import screens.MenuFail;

public class MenuResponseFormatter implements MenuPresenter{

    @Override
    public MenuResponseModel prepareSuccessView(MenuResponseModel user) {
        return new MenuResponseModel(user.getUser(), user.getBalance(), user.isInGame(), user.isLoggedIn(), user.isRulesVisible());
    }

    @Override
    public MenuResponseModel prepareFailView(String error) {
        throw new MenuFail(error);
    }
}
