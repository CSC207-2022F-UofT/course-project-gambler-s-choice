package game_use_case;

import admin_menu_use_case.AdminEditGateway;
import admin_menu_use_case.AdminFileChecker;
import java.io.IOException;

/**
 * This class represents the case where the user leaves the game. This will create a new game state.
 */
public class LeaveInteractor implements LeaveInputBoundary{

    private final LeavePresenter leavePresenter;

    private final AdminEditGateway editGateway;

    /**
     * Constructor for the use case
     * creates an object with the inputted callPresenter and gameFactory
     */
    public LeaveInteractor(LeavePresenter leavePresenter) {
        AdminEditGateway editGateway1;
        this.leavePresenter = leavePresenter;
        try {
            editGateway1 = new AdminFileChecker("src/users.txt");
        }
        catch (IOException e) {
            editGateway1 = null;
        }

        this.editGateway = editGateway1;
    }

    /**
     * Given the Request model, this method will extract relevant information, use it to update the player's balance,
     * and then return the boolean value false as a flag to exit the game screen
     * @param input the request model passed in as input
     * @return the
     */
    @Override
    public boolean create(RequestModel input) {

        String name = "player" + input.getCurrentPlayer();
        this.editGateway.editByName(name, input.getPlayerBalance()[input.getCurrentPlayer()]);

        return leavePresenter.exitGame(false);
    }
}
