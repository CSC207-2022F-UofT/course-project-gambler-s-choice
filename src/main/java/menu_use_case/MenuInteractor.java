package menu_use_case;


public class MenuInteractor implements MenuInputBoundary{

    final MenuDSGateway menuDSGateway;
    final MenuPresenter menuPresenter;

    public MenuInteractor(MenuDSGateway menuDSGateway, MenuPresenter menuPresenter){
        this.menuDSGateway = menuDSGateway;
        this.menuPresenter = menuPresenter;
    }

    @Override
    public MenuResponseModel create(MenuRequestModel menuRequestModel){
        if (menuRequestModel.getInput().equals("Play")){
            if (!menuDSGateway.sufficientBalance(menuRequestModel.getUser())){
                return menuPresenter.prepareFailView("Insufficient Funds on Account");
            } else {
                String username = menuRequestModel.getUser();
                int balance = menuDSGateway.getBalance(username);
                return new MenuResponseModel(username, balance, true, true, menuRequestModel.isRulesVisible());
            }
        } else if (menuRequestModel.getInput().equals("Log out")){
            return new MenuResponseModel(null, 0, false, false, menuRequestModel.isRulesVisible());
        } else if (menuRequestModel.getInput().equals("Help")){
            String username = menuRequestModel.getUser();
            int balance = menuDSGateway.getBalance(username);
            return new MenuResponseModel(username, balance, false, true, !menuRequestModel.isRulesVisible());
        } else {
            String username = menuRequestModel.getUser();
            int balance = menuDSGateway.getBalance(username);
            return new MenuResponseModel(username, balance, false, true, menuRequestModel.isRulesVisible());
        }
    }
}
