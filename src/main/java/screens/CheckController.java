package screens;

import game_entities.Player;
import game_use_case.CheckInputBoundary;
import game_use_case.CheckRequestModel;
import game_use_case.CheckResponseModel;

public class CheckController {
    final CheckInputBoundary input;

    public CheckController(CheckInputBoundary input) {
        this.input = input;
    }

    CheckResponseModel create(int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance, String[] card1,
                              String[] card2, String[] boardCard, String[] card1PNG, String[] card2PNG,
                              String[] boardCardPNG, int currentBet, boolean[] isActive, int[] playerBets,
                              String[] deck) {
        CheckRequestModel requestModel = new CheckRequestModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, boardCard, card1PNG, card2PNG, boardCardPNG, currentBet, isActive, playerBets, deck);
        return input.create(requestModel);
    }
}
