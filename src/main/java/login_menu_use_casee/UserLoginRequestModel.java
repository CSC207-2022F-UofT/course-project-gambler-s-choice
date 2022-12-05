package login_menu_use_casee;

public class UserLoginRequestModel {

    private final String user;
    private final String password;

    public UserLoginRequestModel(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
