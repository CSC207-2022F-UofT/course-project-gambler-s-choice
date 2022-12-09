package screens;

import game_use_case.LeaveInputBoundary;
import game_use_case.RequestModel;

/**
 * Controller used by the Leave use case
 */
public class LeaveController {

    final LeaveInputBoundary input;

    /**
     * Constructor of the controller which initializes the necessary gateway
     * @param input the input boundary for the bet
     */
    public LeaveController(LeaveInputBoundary input) {
        this.input = input;
    }

    /**
     * Creates the necessary response for the next game screen
     * @param currentPlayer The player whose turn it currently is
     * @param firstPlayer The player who will start the round
     * @param lastToBet The player who will end the round
     * @param playerBalance The balance of the players
     * @param card1 The left card of the players
     * @param card2 The right card of the players
     * @param tableCard The cards that are displayed on the table at this state
     * @param card1PNG The image link of the left card of the player
     * @param card2PNG The image link of the right card of the player
     * @param tableCardPNG The image links of the cards on the table
     * @param currentBet The value the current bet is at
     * @param isActive Whether the turn is active or not to decide when to update the screen
     * @param playerBets The total bets from each player at the table
     * @param deck The cards remaining in the deck
     * @param bet The current users bet input field
     * @param user The name of the user who is playing while logged in
     * @return The response model that does the dependency inversion for the game entity
     */
    boolean create(int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance, String[] card1,
                         String[] card2, String[] tableCard, String[] card1PNG, String[] card2PNG,
                         String[] tableCardPNG, int currentBet, boolean[] isActive, int[] playerBets,
                         String[] deck, String bet, String user) {
        RequestModel requestModel = new RequestModel(currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets, deck, bet, user);
        return input.create(requestModel);
    }
}
