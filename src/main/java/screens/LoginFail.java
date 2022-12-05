package screens;

public class LoginFail extends RuntimeException{
    public LoginFail(String error){
        super(error);
    }
}
