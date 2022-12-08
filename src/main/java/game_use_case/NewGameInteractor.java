package game_use_case;

import game_entities.GameFactoryInterface;
import game_entities.GameInterface;

public class NewGameInteractor implements NewGameInputBoundary {
    private final NewGamePresenter newGamePresenter;
    private final GameFactoryInterface gameFactory;
    private String user;

    public NewGameInteractor(NewGamePresenter newGamePresenter, GameFactoryInterface gameFactory) {
        this.newGamePresenter = newGamePresenter;
        this.gameFactory = gameFactory;
    }

    public ResponseModel create(RequestModel input) {
        user = input.getUser();
        GameInterface game = this.gameFactory.create(input.getCurrentPlayer(), input.getFirstPlayer(),
                input.getLastToBet(), input.getPlayerBalance(), input.getCard1(), input.getCard2(),
                input.getTableCard(), input.getCurrentBet(), input.getIsActive(), input.getPlayerBets(),
                input.getDeck());
        game.newGame();

        //response model
        int length = game.getPlayers().length;
        String[] card1 = new String[length];
        String[] card2 = new String[length];
        String[] tableCard = new String[5];
        String[] card1PNG = new String[length];
        String[] card2PNG = new String[length];
        String[] tableCardPNG = new String[5];
        int[] playerBalance = new int[length];
        int currentPlayer = 0;
        int firstPlayer = 0;
        int lastToBet = 0;
        int currentBet = 0;
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
        return newGamePresenter.prepareSuccessView(response);
    }

    @Override
    public String getUser() {
        return user;
    }
}
