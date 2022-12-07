package game_use_case;

import game_entities.GameFactoryInterface;
import game_entities.GameInterface;

public class BetInteractor implements BetInputBoundary {
    final BetPresenter betPresenter;
    final GameFactoryInterface gameFactory;


    public BetInteractor(BetPresenter outputBoundary, GameFactoryInterface gameFactory) {
        this.betPresenter = outputBoundary;
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
        if (inputData.getBet() <= inputData.getCurrentBet() &&
                inputData.getBet() < game.getPlayers()[inputData.getCurrentPlayer()].getBalance()) {
            // Implies player has not gone all in
            return betPresenter.prepareFailView("Cannot bet less than previous bet");
        }
        if (inputData.getBet() > game.getPlayers()[inputData.getCurrentPlayer()].getBalance()) {
            return betPresenter.prepareFailView("Cannot bet more than balance");
        }
        // If the previous checks pass, the player is allowed to bet
        game.getPlayers()[inputData.getCurrentPlayer()].bet(inputData.getBet());
        game.getPool().addMoney(game.getPlayers()[inputData.getCurrentPlayer()], inputData.getBet());

        // Common method used to move onto next player
        game.nextPlayer();
        if (game.getCurrentPlayer() == -1) {
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
        int currentPlayer = inputData.getCurrentPlayer();
        currentPlayer += 1;
        currentPlayer %= 2;
        int firstPlayer = game.getFirstPlayer();
        int lastToBet = game.lastToBet();
        int currentBet = inputData.getBet();
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
        if (inputData.getBet() == game.getPlayers()[inputData.getCurrentPlayer()].getBalance()) {
            isActive[inputData.getCurrentPlayer()] = false;
        }

        ResponseModel response = new ResponseModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets, deck,
                true);
        return betPresenter.prepareSuccessView(response);
    }
}
