package admin_menu_use_case;

public class AdminEditInteractor implements AdminEditBalanceInputBoundary{
    final AdminEditGateway adminEditGateway;
    final AdminEditPresenter adminEditPresenter;

    public AdminEditInteractor(AdminEditGateway adminEditGateway, AdminEditPresenter adminEditPresenter){
        this.adminEditGateway = adminEditGateway;
        this.adminEditPresenter = adminEditPresenter;
    }



    @Override
    public AdminEditResponseModel create(AdminEditBalanceModel editBalanceModel) {
        if (editBalanceModel.getInput().equals("Play")){
            if (adminEditGateway.sufficientBalance(editBalanceModel.getUser())){
                return adminEditPresenter.prepareFailView("Insufficient Funds on Account");
            }
            else {
                String username = editBalanceModel.getUser();
                int balance = adminEditGateway.getBalance(username);
                return new AdminEditResponseModel(username,
                        balance, true, true, editBalanceModel.isRulesVisible());
            }
        } else if (editBalanceModel.getInput().equals("Log Out")){
            String username = editBalanceModel.getUser();
            int balance = adminEditGateway.getBalance(username);
            return new AdminEditResponseModel(username,
                    balance, false, false, editBalanceModel.isRulesVisible());
        } else if (editBalanceModel.getInput().equals("Help")){String username = editBalanceModel.getUser();
            int balance = adminEditGateway.getBalance(username);
            boolean rulesVisible = !editBalanceModel.isRulesVisible();
            return new AdminEditResponseModel(username,
                    balance, true, false, rulesVisible);
        } else if (editBalanceModel.getInput().equals("Edit User")) {
            if (!(adminEditGateway.existsByName(editBalanceModel.getUser()))) {
                return adminEditPresenter.prepareFailView("User not found");
            }
            else {
                if (adminEditGateway.validBalance(editBalanceModel.getBalance())) {
                    String username = editBalanceModel.getUser();
                    int balance = Integer.parseInt(editBalanceModel.getBalance());
                    try {
                        adminEditGateway.editByName(username, balance); //Edits the actual user balance
                    } catch (Exception e) {
                        return adminEditPresenter.prepareFailView(e.toString());
                    }
                    return new AdminEditResponseModel(
                            username, balance, true, false, editBalanceModel.isRulesVisible());
                }
                else{
                    return adminEditPresenter.prepareFailView("Invalid balance amount");
                }
            }
        } else {
            String username = editBalanceModel.getUser();
            int balance = adminEditGateway.getBalance(username);
            return new AdminEditResponseModel(username,
                    balance, true, false, editBalanceModel.isRulesVisible());
        }
    }
}
