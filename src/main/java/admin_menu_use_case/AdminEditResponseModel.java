package admin_menu_use_case;

public class AdminEditResponseModel {
    private String user;
    private final int balance;
    private boolean loggedIn;
    private boolean rulesVisible;
    private boolean inGame;

    public AdminEditResponseModel(String user, int balance, boolean loggedIn, boolean inGame, boolean rulesVisible){
        this.user = user;
        this.balance = balance;
        this.loggedIn = loggedIn;
        this.inGame = inGame;
        this.rulesVisible = rulesVisible;
    }

    /**
     * Reports the username of the user
     * @return the username of the user
     */
    String getUser() {
        return user;
    }
    /**
     * Reports the balance of the user
     * @return the balance of the user
     */
    public int getBalance() {
        return balance;
    }
    /**
     * Returns whether the rules are visible
     * @return true iff the rules are visible
     */
    public boolean isRulesVisible() {
        return rulesVisible;
    }
    /**
     * Returns whether the user is in the game
     * @return true iff the user is in the game
     */
    public boolean isInGame() {
        return inGame;
    }
    /**
     * Returns whether the user is logged in
     * @return true iff the user is logged in
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
