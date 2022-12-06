package login_use_case;

public class UserRequestModel {
    /**
     * UserRequestModel is a data structure that stores user information for the user to register or login.
     */
    private String username;
    private String password;
    private String type;

    public UserRequestModel(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getType(){
        return this.type;
    }
}