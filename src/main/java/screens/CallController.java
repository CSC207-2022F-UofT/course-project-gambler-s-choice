package screens;

import game_use_case.BetInputBoundary;
import game_use_case.CallInputBoundary;
import game_use_case.RequestModel;
import game_use_case.ResponseModel;

public class CallController {
    final CallInputBoundary input;

    public CallController(CallInputBoundary input) {
        this.input = input;
    }

    ResponseModel create(int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance, String[] card1,
                         String[] card2, String[] tableCard, String[] card1PNG, String[] card2PNG,
                         String[] tableCardPNG, int currentBet, boolean[] isActive, int[] playerBets,
                         String[] deck, String bet, String user) {
        RequestModel requestModel = new RequestModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets, deck, bet, user);
        return input.create(requestModel);
    }
}
