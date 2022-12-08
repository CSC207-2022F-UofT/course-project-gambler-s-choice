package game_use_case;

import admin_menu_use_case.AdminEditGateway;
import admin_menu_use_case.AdminFileChecker;
import admin_menu_use_case.AdminFileChecker.*;
import game_entities.PlayerInterface;

import java.io.IOException;

public class LeaveInteractor implements LeaveInputBoundary{

    private final LeavePresenter leavePresenter;

    private final AdminEditGateway editGateway;

    private final String name;

    private final PlayerInterface player;

    public LeaveInteractor(LeavePresenter leavePresenter, String name, PlayerInterface player) {
        AdminEditGateway editGateway1;
        this.leavePresenter = leavePresenter;
        try {
            editGateway1 = new AdminFileChecker("users.txt");
        }
        catch (IOException e) {
            editGateway1 = null;
        }

        this.player = player;
        this.editGateway = editGateway1;
        this.name = name;
    }

    @Override
    public boolean create(RequestModel input) {
        this.editGateway.editByName(this.name, input.getPlayerBalance()[input.getCurrentPlayer()]);

        return leavePresenter.exitGame(false);
    }
}
