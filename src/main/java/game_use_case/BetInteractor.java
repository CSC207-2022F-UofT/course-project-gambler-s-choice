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
    public ResponseModel create(RequestModel input) {

        // Change last to bet to this player
        GameInterface game = this.gameFactory.create(input.getCurrentPlayer(), input.getFirstPlayer(),
                input.getCurrentPlayer(), // Init game with lastToBet as current player
                input.getPlayerBalance(),
                input.getCard1(), input.getCard2(), input.getTableCard(),
                input.getCurrentBet(), input.getIsActive(), input.getPlayerBets(),
                input.getDeck());
        int bet = 0;
        try {
            bet = Integer.parseInt(input.getBet());
        } catch (Exception e) {
            return betPresenter.prepareFailView("Please enter a valid bet amount");
        }
        // Logic goes here
        if (bet <= 0) {
            return betPresenter.prepareFailView("Please enter a positive bet amount");
        }
        if (bet <= input.getCurrentBet() &&
                bet < game.getPlayers()[input.getCurrentPlayer()].getBalance()) {
            // Implies player has not gone all in
            return betPresenter.prepareFailView("Cannot bet less than previous bet");
        }
        if (bet > game.getPlayers()[input.getCurrentPlayer()].getBalance()) {
            return betPresenter.prepareFailView("Cannot bet more than balance");
        }
        // If the previous checks pass, the player is allowed to bet
        game.getPlayers()[input.getCurrentPlayer()].bet(bet);
        game.getPool().addMoney(game.getPlayers()[input.getCurrentPlayer()], bet);

        // If player has gone all in, set them inactive
        if (0 == game.getPlayers()[input.getCurrentPlayer()].getBalance()) {
            game.getActive()[input.getCurrentPlayer()] = false;
        }

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
        int currentPlayer = game.getCurrentPlayer();
        int firstPlayer = game.getFirstPlayer();
        int lastToBet = game.lastToBet();
        int currentBet = bet;
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
        return betPresenter.prepareSuccessView(response);
    }
}
