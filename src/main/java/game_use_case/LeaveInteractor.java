package game_use_case;

import admin_menu_use_case.AdminEditGateway;
import admin_menu_use_case.AdminFileChecker;
import admin_menu_use_case.AdminFileChecker.*;
import game_entities.PlayerInterface;

import java.io.IOException;

public class LeaveInteractor implements LeaveInputBoundary{

    private final LeavePresenter leavePresenter;

    private final AdminEditGateway editGateway;

    public LeaveInteractor(LeavePresenter leavePresenter) {
        AdminEditGateway editGateway1;
        this.leavePresenter = leavePresenter;
        try {
            editGateway1 = new AdminFileChecker("src/main/users.txt");
        }
        catch (IOException e) {
            editGateway1 = null;
        }

        this.editGateway = editGateway1;
    }

    @Override
    public boolean create(RequestModel input) {

        String name = input.getUser();
        System.out.println(editGateway.getBalance(name));
        int balance = editGateway.getBalance(name) - input.getPlayerBalance()[0];
        System.out.println(balance);
        System.out.println(name);

        this.editGateway.editByName(name, balance);

        return leavePresenter.exitGame(false);
    }
}
