package menu_use_case;

import login_menu_use_casee.UserLoginResponseModel;

public interface MenuPresenter {

    MenuResponseModel prepareSuccessView(MenuResponseModel user);

    MenuResponseModel prepareFailView(String error);
}
