package admin_menu_use_case;


import screens.EditFail;

public class AdminEditResponseFormatter implements  AdminEditPresenter{
    @Override
    public AdminEditResponseModel prepareSuccessView(AdminEditResponseModel user){
        return new AdminEditResponseModel(user.getUser(), user.getBalance(), user.isLoggedIn(), user.isInGame(), user.isRulesVisible());
    }

    public AdminEditResponseModel prepareFailView(String error){
        throw new EditFail(error);
    }
}
