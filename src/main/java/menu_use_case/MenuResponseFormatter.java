package menu_use_case;

import screens.MenuFail;

public class MenuResponseFormatter implements MenuPresenter{

    /**
     * Returns a new MenuResponseModel with the given user's information
     * @param user the given MenuResponseModel
     * @return a new MenuResponseModel with the given user's information
     */
    @Override
    public MenuResponseModel prepareSuccessView(MenuResponseModel user) {
        return new MenuResponseModel(user.getUser(), user.getBalance(), user.isInGame(), user.isLoggedIn(), user.isRulesVisible());
    }

    /**
     * Throws a login fail error
     * @param error the given error message
     * @return a login fail error with the given error message
     */
    @Override
    public MenuResponseModel prepareFailView(String error) {
        throw new MenuFail(error);
    }
}
