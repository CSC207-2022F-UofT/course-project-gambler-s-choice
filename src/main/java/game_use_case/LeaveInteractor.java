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
            editGateway1 = new AdminFileChecker("users.txt");
        }
        catch (IOException e) {
            editGateway1 = null;
        }

        this.editGateway = editGateway1;
    }

    @Override
    public boolean create(RequestModel input) {

        String name = "player" + input.getCurrentPlayer();
        this.editGateway.editByName(name, input.getPlayerBalance()[input.getCurrentPlayer()]);

        return leavePresenter.exitGame(false);
    }
}
