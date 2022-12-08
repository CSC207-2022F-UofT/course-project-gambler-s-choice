package menu_use_case;

public class MenuRequestModel {

    private final String user;
    private final String input;
    private final boolean rulesVisible;

    public MenuRequestModel(String user, String input, boolean rulesVisible){
        this.user = user;
        this.input = input;
        this.rulesVisible = rulesVisible;
    }

    public String getUser() {
        return user;
    }

    public String getInput() {
        return input;
    }

    public boolean isRulesVisible() {
        return rulesVisible;
    }
}
