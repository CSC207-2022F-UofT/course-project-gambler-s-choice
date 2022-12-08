package menu_use_case;

public interface MenuPresenter {

    MenuResponseModel prepareSuccessView(MenuResponseModel user);

    MenuResponseModel prepareFailView(String error);
}
