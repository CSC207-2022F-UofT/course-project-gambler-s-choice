package game_use_case;

import game_entities.*;

import java.util.Arrays;

/**
 * This class represents the case where the user folds in the game. This will create a new game state.
 */

public class FoldInteractor implements FoldInputBoundary{

    private final FoldPresenter foldPresenter;

    private final GameFactoryInterface gameFactory;

    /**
     * Fold Constructors
     * creates an object with the inputted callPresenter and gameFactory
     */
    public FoldInteractor(FoldPresenter foldPresenter, GameFactoryInterface gameFactory) {
        this.foldPresenter = foldPresenter;
        this.gameFactory = gameFactory;
    }

    /**
     * Creates a response model with the updated game values after the input has been processed
     * @param input the inputted request model
     * @return a response model with the updated Game values
     */
    @Override
    public ResponseModel create(RequestModel input) {
        GameInterface game = this.gameFactory.create(input.getCurrentPlayer(), input.getFirstPlayer(),
                input.getLastToBet(), input.getPlayerBalance(), input.getCard1(), input.getCard2(),
                input.getTableCard(), input.getCurrentBet(), input.getIsActive(), input.getPlayerBets(),
                input.getDeck());

        int foldedPlayer = input.getCurrentPlayer();
        // Don't let the player fold if they go first
        if (game.getCurrentWager() == 0) {
            return foldPresenter.prepareFailView("Don't fold. You should check instead");
        }

        // Set player to inactive
        game.getActive()[input.getCurrentPlayer()] = false;

        // Check if everyone is inactive
        boolean allInactive = true;
        for (boolean playerActive : game.getActive()) {
            if (playerActive) {
                allInactive = false;
            }
        }
        // If everyone is inactive, force everyone who has not folded back to active
        if (allInactive) {
            for (int i = 0; i < game.getActive().length; i++) {
                if (game.getPlayers()[i].getBalance() == 0) {
                    game.getActive()[i] = true;
                }
            }
        }

        // Check if everyone is inactive again
        allInactive = true;
        for (boolean playerActive : game.getActive()) {
            if (playerActive) {
                allInactive = false;
            }
        }
        // If everyone is inactive, this implies everyone folded; force everyone back to active
        if (allInactive) {
            Arrays.fill(game.getActive(), true);
        }

        game.nextPlayer();
        if (game.getCurrentPlayer() == input.getLastToBet()) {
            game.nextRound();
        } else if (game.getCurrentPlayer() == -1) {
            game.nextRound();
        }

        //response model
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

        isActive[foldedPlayer] = false;

        ResponseModel response = new ResponseModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets, deck,
                true, input.getUser());
        return foldPresenter.prepareSuccessView(response);
    }
}
