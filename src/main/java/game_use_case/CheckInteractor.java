package game_use_case;

import game_entities.*;

/**
 * This class represents when the user checks in the game. This will create a new game state.
 */
public class CheckInteractor implements CheckInputBoundary {

    final CheckPresenter checkPresenter;
    final GameFactoryInterface gameFactory;

    /**
     * CheckConstructor
     * creates an object with the inputted callPresenter and gameFactory
     */
    public CheckInteractor(CheckPresenter outputBoundary, GameFactoryInterface gameFactory) {
        this.checkPresenter = outputBoundary;
        this.gameFactory = gameFactory;
    }

    /**
     * This method will take in a request model and apply a "check" transformation to it.
     * It will then output the new values as a response value.
     * @param input the request model passed in
     * @return a response model with changed values
     */
    @Override
    public ResponseModel create(RequestModel input) {

        GameInterface game = this.gameFactory.create(input.getCurrentPlayer(), input.getFirstPlayer(),
                input.getLastToBet(), input.getPlayerBalance(),
                input.getCard1(), input.getCard2(), input.getTableCard(),
                input.getCurrentBet(), input.getIsActive(), input.getPlayerBets(),
                input.getDeck());

        if (game.getCurrentWager() != 0) {
            return checkPresenter.prepareFailView("Cannot check when current wager is not 0");
        }

        // Common method used to move onto next player
        game.nextPlayer();
        if (game.getCurrentPlayer() == input.getLastToBet()) {
            game.nextRound();
        } else if (game.getCurrentPlayer() == -1) {
            game.nextRound();
        }

        // Converting Game information into ResponseModel
        int length = game.getPlayers().length;
        String[] card1 = new String[length];
        String[] card2 = new String[length];
        String[] tableCard = new String[5];
        String[] card1PNG = new String[length];
        String[] card2PNG = new String[length];
        String[] tableCardPNG = new String[5];
        int[] playerBalance = new int[length];
        int currentPlayer = game.getCurrentPlayer();
        int firstPlayer = game.getFirstPlayer();
        int lastToBet = game.lastToBet();
        int currentBet = game.getCurrentWager();
        for (int i = 0; i < length; i++) {
            card1[i] = game.getPlayers()[i].getCards()[0].toString();
            card2[i] = game.getPlayers()[i].getCards()[1].toString();
            card1PNG[i] = game.getPlayers()[i].getCards()[0].getPNG();
            card2PNG[i] = game.getPlayers()[i].getCards()[1].getPNG();
            playerBalance[i] = game.getPlayers()[i].getBalance();
        }
        int[] playerBets = game.getPool().getBets();
        boolean[] isActive = game.getActive();
        for (int i = 0; i < 5; i++) {
            if (game.getTableCards()[i] != null) {
                tableCard[i] = game.getTableCards()[i].toString();
                tableCardPNG[i] = game.getTableCards()[i].getPNG();
            }
        }
        String[] deck = game.getDeck().deckAsStringArray();

        ResponseModel response = new ResponseModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets, deck,
                true, input.getUser());
        return checkPresenter.prepareSuccessView(response);
    }
}
