package game_use_case;

import game_entities.*;

public class CheckInteractor implements CheckInputBoundary {

    final CheckPresenter checkPresenter;
    final GameFactoryInterface gameFactory;


    public CheckInteractor(CheckPresenter outputBoundary, GameFactoryInterface gameFactory) {
        this.checkPresenter = outputBoundary;
        this.gameFactory = gameFactory;
    }

    @Override
    public ResponseModel create(RequestModel inputData) {

//        GameInterface game = this.gameFactory.create(inputData.getCurrentPlayer(), inputData.getFirstPlayer(),
//                inputData.getLastToBet(), inputData.getPlayerBalance(),
//                inputData.getCard1(), inputData.getCard2(), inputData.getTableCard(),
//                inputData.getCurrentBet(), inputData.getIsActive(), inputData.getPlayerBets(),
//                inputData.getDeck());

//        if (game.getCurrentWager() != 0) {
//            return checkPresenter.prepareFailView("Cannot check when current wager is not 0");
//        }
        // TODO Checking method here
        //game.check();

//        Player curr = game.getPlayers()[inputData.getPlayer()];
//        String card1 = curr.getCards()[0].getPNG();
//        String card2 = curr.getCards()[1].getPNG();
//        Card[] tableCards = game.getTableCards();
//        int amount = 0;
//        for (Card card : tableCards) {
//            if (card != null) {
//                amount++;
//            }
//        }
//        String[] table = new String[amount];
//        for (int i = 0; i < 5; i++) {
//            if (tableCards[i] != null) {
//                table[i] = tableCards[i].getPNG();
//            }
//        }
//        int currentBet = game.getCurrentWager();
//        int totalBet = game.totalBet();





        Player player1 = new Player(new Card("5", "H"), new Card("7", "H"));
        Player player2 = new Player(new Card("K", "D"), new Card("9", "C"));

        Player[] players = {player1, player2};
        Game game = new Game(players);

        int length = game.getPlayers().length;
        String[] card1 = new String[length];
        String[] card2 = new String[length];
        String[] tableCard = new String[5];
        String[] card1PNG = new String[length];
        String[] card2PNG = new String[length];
        String[] tableCardPNG = new String[5];
        int[] playerBalance = new int[length];
        int[] playerBets = new int[length];
        boolean[] isActive = new boolean[length];

        int currentPlayer = inputData.getCurrentPlayer();
        currentPlayer += 1;
        currentPlayer %= 2;
        int firstPlayer = 0;
        int lastToBet = 0;
        int currentBet = 0;

        for (int i = 0; i < length; i++) {
            card1[i] = game.getPlayers()[i].getCards()[0].toString();
            card2[i] = game.getPlayers()[i].getCards()[1].toString();
            card1PNG[i] = game.getPlayers()[i].getCards()[0].getPNG();
            card2PNG[i] = game.getPlayers()[i].getCards()[1].getPNG();
            playerBalance[i] = 0;
            playerBets[i] = 0;
            isActive[i] = true;
            // System.out.println(card1[i] + " " + card2[i] + " " + card1PNG[i] + " " + card2PNG[i]);
        }
        for (int i = 0; i < 5; i++) {
            tableCard[i] = game.getTableCards()[i].toString();
            tableCardPNG[i] = game.getTableCards()[i].getPNG();
            // System.out.println(tableCard[i] + " " + tableCardPNG[i]);
        }
        String[] deck = {"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DX", "DJ", "DQ", "DK",
                "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CX", "CJ", "CQ", "CK",
                "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "HX", "HJ", "HQ", "HK",
                "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "SX", "SJ", "SQ", "SK"};










        ResponseModel response = new ResponseModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets, deck,
                true);
        return checkPresenter.prepareSuccessView(response);
    }
}
