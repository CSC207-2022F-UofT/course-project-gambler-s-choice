package admin_menu_use_case;


import screens.EditFail;

/**
 * creates the AdminEditResponsemodels such that they can return a success or fail view
 */
public class AdminEditResponseFormatter implements  AdminEditPresenter{

    /**
     * Returns a new AdminEditResponseModel with the given user's information
     * @param user the given UserLoginResponseModel
     * @return a new UserLoginResponseModel with the given user's information
     */
    @Override
    public AdminEditResponseModel prepareSuccessView(AdminEditResponseModel user){
        return new AdminEditResponseModel(user.getUser(), user.getBalance(), user.isLoggedIn(), user.isInGame(), user.isRulesVisible());
    }

    /**
     * Throws a login fail error
     * @param error the given error message
     * @return a login fail error with the given error message
     */
    public AdminEditResponseModel prepareFailView(String error){
        throw new EditFail(error);
    }
}
