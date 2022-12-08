package admin_menu_use_case;

public class AdminEditInteractor implements AdminEditBalanceInputBoundary{
    final AdminEditGateway adminEditGateway;
    final AdminEditPresenter adminEditPresenter;

    public AdminEditInteractor(AdminEditGateway adminEditGateway, AdminEditPresenter adminEditPresenter){
        this.adminEditGateway = adminEditGateway;
        this.adminEditPresenter = adminEditPresenter;
    }


    /**
     * If the input is "Play", the system checks if the user has sufficient funds. If so, the user logs in to the game.
     * If the input is "Log Out", the system logs the user out.
     * If the input is "Help", the system displays the rules and sets the inGame status false
     * If the input is "Edit User", the system checks if the username exists in the database. If so, the admin edits
     * the user's balance
     * @param editBalanceModel the given AdminEditBalanceModel
     * @return an AdminEditResponseModel
     */
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
        } else if (editBalanceModel.getInput().equals("Help")){
            String username = editBalanceModel.getUser();
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
