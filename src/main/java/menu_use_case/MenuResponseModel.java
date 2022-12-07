package menu_use_case;

public class MenuResponseModel {

    private boolean inGame;
    private String user;
    private int balance;
    private boolean loggedIn;
    private boolean rulesVisible;

    public MenuResponseModel(String user, int balance, boolean inGame, boolean loggedIn, boolean rulesVisible){
        this.inGame = inGame;
        this.user = user;
        this.balance = balance;
        this.loggedIn = loggedIn;
        this.rulesVisible = rulesVisible;
    }

    public boolean isInGame() {
        return inGame;
    }

    public String getUser() {
        return user;
    }

    public int getBalance() {
        return balance;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isRulesVisible() {
        return rulesVisible;
    }
}
