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
    String getUser() {
        return user;
    }
    public int getBalance() {
        return balance;
    }

    public boolean isRulesVisible() {
        return rulesVisible;
    }

    public boolean isInGame() {
        return inGame;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
