package screens;

import game_use_case.FoldInputBoundary;
import game_use_case.FoldPresenter;
import game_use_case.RequestModel;
import game_use_case.ResponseModel;

public class FoldController {
    final FoldInputBoundary input;

    public FoldController(FoldInputBoundary input) {
        this.input = input;
    }

    ResponseModel create(int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance, String[] card1,
                         String[] card2, String[] tableCard, String[] card1PNG, String[] card2PNG,
                         String[] tableCardPNG, int currentBet, boolean[] isActive, int[] playerBets,
                         String[] deck, String bet) {
        RequestModel requestModel = new RequestModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets,
                deck, bet);
        return input.create(requestModel);

    }
}
