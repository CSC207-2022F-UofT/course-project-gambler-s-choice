package screens;

import game_use_case.NewGameInputBoundary;
import game_use_case.RequestModel;
import game_use_case.ResponseModel;

/**
 * Controller used by the New Game use case
 */
public class NewGameController {

    final NewGameInputBoundary input;

    /**
     * Constructor of the controller which initializes the necessary gateway
     * @param input the input boundary for the bet
     */
    public NewGameController(NewGameInputBoundary input) {
        this.input = input;
    }

    /**
     * Creates a newly initialized game
     * @param numberOfPlayers The amount of players playing
     * @return The response model that does the dependency inversion for the game entity
     */
    ResponseModel create(int numberOfPlayers) {
        String[] card1 = new String[numberOfPlayers];
        String[] card2 = new String[numberOfPlayers];
        int[] playerBalance = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            card1[i] = "SA";
            card2[i] = "SA";
            playerBalance[i] = 100;
        }
        RequestModel requestModel = new RequestModel(0, 0, 0, playerBalance,
                card1, card2, new String[]{"SA", "SA", "SA", "SA", "SA"},
                new String[numberOfPlayers], new String[numberOfPlayers], new String[5], 0,
                new boolean[numberOfPlayers], new int[numberOfPlayers], new String[]{"SA"}, "0", input.getUser());
        return input.create(requestModel);
    }
}
