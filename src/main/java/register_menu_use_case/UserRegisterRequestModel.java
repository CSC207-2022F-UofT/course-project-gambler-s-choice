package register_menu_use_case;

public class UserRegisterRequestModel {

    private final String user;
    private final String password;
    private final String password2;

    public UserRegisterRequestModel(String user, String password, String password2){
        this.user = user;
        this.password = password;
        this.password2 = password2;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }
}
