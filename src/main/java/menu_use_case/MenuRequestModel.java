package menu_use_case;

/**
 * The MenuRequestModel is a data structure that stores the username, input and whether the rules are visible
 */
public class MenuRequestModel {

    private final String user;
    private final String input;
    private final boolean rulesVisible;

    public MenuRequestModel(String user, String input, boolean rulesVisible){
        this.user = user;
        this.input = input;
        this.rulesVisible = rulesVisible;
    }

    /**
     * Reports the username
     * @return the username of the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Reports the input chosen
     * @return the input chosen
     */
    public String getInput() {
        return input;
    }

    /**
     * Returns whether the rules are visible
     * @return true iff the rules are visible
     */
    public boolean isRulesVisible() {
        return rulesVisible;
    }
}
