package game_use_case;

import game_entities.Card;
import game_entities.Game;
import game_entities.Player;

public class CheckInteractor implements CheckInputBoundary {

    final CheckPresenter checkPresenter;
    final Game game;


    public CheckInteractor(CheckPresenter outputBoundary, Game game) {
        this.checkPresenter = outputBoundary;
        this.game = game;
    }

    @Override
    public CheckResponseModel create(CheckRequestModel inputData) {
        if (game.getCurrentWager() != 0) {
            return checkPresenter.prepareFailView("Cannot check when current wager is not 0");
        }
        // TODO Checking method here
        game.check();

        Player curr = game.getPlayers()[inputData.getPlayer()];
        String card1 = curr.getCards()[0].getPNG();
        String card2 = curr.getCards()[1].getPNG();
        Card[] tableCards = game.getBoardCards();
        int amount = 0;
        for (Card card : tableCards) {
            if (card != null) {
                amount++;
            }
        }
        String[] table = new String[amount];
        for (int i = 0; i < 5; i++) {
            if (tableCards[i] != null) {
                table[i] = tableCards[i].getPNG();
            }
        }
        int currentBet = game.getCurrentWager();
        int totalBet = game.totalBet();

        CheckResponseModel response = new CheckResponseModel(card1, card2, table, currentBet, totalBet);
        return checkPresenter.prepareSuccessView(response);
    }
}
