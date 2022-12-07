package game_use_case;

import game_entities.GameFactoryInterface;
import game_entities.GameInterface;

public class CallInteractor implements CallInputBoundary{
    final CallPresenter callPresenter;
    final GameFactoryInterface gameFactory;


    public CallInteractor(CallPresenter outputBoundary, GameFactoryInterface gameFactory) {
        this.callPresenter = outputBoundary;
        this.gameFactory = gameFactory;
    }

    @Override
    public ResponseModel create(RequestModel inputData) {

        GameInterface game = this.gameFactory.create(inputData.getCurrentPlayer(), inputData.getFirstPlayer(),
                inputData.getLastToBet(), inputData.getPlayerBalance(),
                inputData.getCard1(), inputData.getCard2(), inputData.getTableCard(),
                inputData.getCurrentBet(), inputData.getIsActive(), inputData.getPlayerBets(),
                inputData.getDeck());

        // Logic goes here
        if (inputData.getCurrentBet() == 0) {
            callPresenter.prepareFailView("Cannot call when current bet is 0. Please check instead.");
        }

        if (inputData.getCurrentBet() >= inputData.getPlayerBalance()[inputData.getCurrentPlayer()]) {
            // Case when player has less money than previous bet
            // Player goes all in; their money goes to 0
            game.getPlayers()[inputData.getCurrentPlayer()].bet(
                    inputData.getPlayerBalance()[inputData.getCurrentPlayer()]);
            game.getPool().addMoney(game.getPlayers()[inputData.getCurrentPlayer()],
                    inputData.getPlayerBalance()[inputData.getCurrentPlayer()]);
            game.getActive()[inputData.getCurrentPlayer()] = false;
        } else {
            game.getPlayers()[inputData.getCurrentPlayer()].bet(inputData.getCurrentBet());
            game.getPool().addMoney(game.getPlayers()[inputData.getCurrentPlayer()], inputData.getCurrentBet());
        }

        // Common method used to move onto next player
        game.nextPlayer();
        if (game.getCurrentPlayer() == inputData.getLastToBet()) {
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
        int currentBet = inputData.getCurrentBet();
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
        if (0 == game.getPlayers()[inputData.getCurrentPlayer()].getBalance()) {
            isActive[inputData.getCurrentPlayer()] = false;
        }

        ResponseModel response = new ResponseModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets, deck,
                true);
        return callPresenter.prepareSuccessView(response);
    }
}
