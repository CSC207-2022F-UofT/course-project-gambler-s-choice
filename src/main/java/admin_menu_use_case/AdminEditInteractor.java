package admin_menu_use_case;

public class AdminEditInteractor implements AdminEditBalanceInputBoundary{
    final AdminEditGateway adminEditGateway;
    final AdminEditPresenter adminEditPresenter;

    public AdminEditInteractor(AdminEditGateway adminEditGateway, AdminEditPresenter adminEditPresenter){
        this.adminEditGateway = adminEditGateway;
        this.adminEditPresenter = adminEditPresenter;
    }



    @Override
    public AdminEditResponseModel create(AdminEditBalanceModel editBalanceModel, String txtpath) {
        if (!(adminEditGateway.existsByName(editBalanceModel.getUser()))){
            return adminEditPresenter.prepareFailView("User not found");
        }
        else{
            String username = editBalanceModel.getUser();
            int balance = editBalanceModel.getBalance();
            try {
                adminEditGateway.editByName(txtpath, username, balance); //Edits the actual user balance
            }catch (Exception e){
                return adminEditPresenter.prepareFailView(e.toString());
            }
            AdminEditResponseModel adminResponseModel = new AdminEditResponseModel(username, balance);
            return adminEditPresenter.prepareSuccessView(adminResponseModel);

        }
    }
}