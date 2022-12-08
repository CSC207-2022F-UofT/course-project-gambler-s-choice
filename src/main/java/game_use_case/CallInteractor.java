package game_use_case;

import game_entities.GameFactoryInterface;
import game_entities.GameInterface;

import java.util.Arrays;

public class CallInteractor implements CallInputBoundary{
    final CallPresenter callPresenter;
    final GameFactoryInterface gameFactory;


    public CallInteractor(CallPresenter outputBoundary, GameFactoryInterface gameFactory) {
        this.callPresenter = outputBoundary;
        this.gameFactory = gameFactory;
    }

    @Override
    public ResponseModel create(RequestModel input) {

        GameInterface game = this.gameFactory.create(input.getCurrentPlayer(), input.getFirstPlayer(),
                input.getLastToBet(), input.getPlayerBalance(),
                input.getCard1(), input.getCard2(), input.getTableCard(),
                input.getCurrentBet(), input.getIsActive(), input.getPlayerBets(),
                input.getDeck());

        // Logic goes here
        if (input.getCurrentBet() == 0) {
            callPresenter.prepareFailView("Cannot call when current bet is 0. Please check instead.");
        }

        // Setting the amount the player must bet
        int amountToBet = input.getPlayerBets()[input.getLastToBet()] -
                input.getPlayerBets()[input.getCurrentPlayer()];

        if (amountToBet >= input.getPlayerBalance()[input.getCurrentPlayer()]) {
            // Case when player has less money than previous bet
            // Player goes all in; their money goes to 0
            game.getPlayers()[input.getCurrentPlayer()].bet(
                    input.getPlayerBalance()[input.getCurrentPlayer()]);
            game.getPool().addMoney(game.getPlayers()[input.getCurrentPlayer()],
                    input.getPlayerBalance()[input.getCurrentPlayer()]);
            game.getActive()[input.getCurrentPlayer()] = false;
        } else {
            game.getPlayers()[input.getCurrentPlayer()].bet(amountToBet);
            game.getPool().addMoney(game.getPlayers()[input.getCurrentPlayer()], amountToBet);
        }
        // Check if everyone is inactive
        boolean allInactive = true;
        for (boolean playerActive : game.getActive()) {
            if (playerActive) {
                allInactive = false;
            }
        }
        // If everyone is inactive, force everyone back to active
        if (allInactive) {
            Arrays.fill(game.getActive(), true);
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

        // If player has gone all in, set them inactive
        if (0 == game.getPlayers()[input.getCurrentPlayer()].getBalance()) {
            isActive[input.getCurrentPlayer()] = false;
        }

        ResponseModel response = new ResponseModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets, deck,
                true);
        return callPresenter.prepareSuccessView(response);
    }
}
