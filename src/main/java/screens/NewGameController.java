package screens;

import game_use_case.CheckInputBoundary;
import game_use_case.NewGameInputBoundary;
import game_use_case.RequestModel;
import game_use_case.ResponseModel;

public class NewGameController {

    final NewGameInputBoundary input;

    public NewGameController(NewGameInputBoundary input) {
        this.input = input;
    }

    ResponseModel create(int numberOfPlayers) {
        String[] card1 = new String[numberOfPlayers];
        String[] card2 = new String[numberOfPlayers];
        int[] playerBalance = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            card1[i] = "SA";
            card2[i] = "SA";
            playerBalance[i] = 0;
        }
        RequestModel requestModel = new RequestModel(0, 0, 0, playerBalance,
                card1, card2, new String[]{"SA", "SA", "SA", "SA", "SA"},
                new String[numberOfPlayers], new String[numberOfPlayers], new String[5], 0,
                new boolean[numberOfPlayers], new int[numberOfPlayers], new String[]{"SA"}, "0");
        return input.create(requestModel);
    }
}
