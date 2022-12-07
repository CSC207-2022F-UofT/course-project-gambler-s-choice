package menu_use_case;

public interface AdminEditPresenter {
    AdminEditResponseModel prepareSuccessView(AdminEditResponseModel user);

    AdminEditResponseModel prepareFailView(String error);
}
