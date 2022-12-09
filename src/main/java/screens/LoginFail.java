package screens;

/**
 * Specific error for the login use case
 */
public class LoginFail extends RuntimeException{

    /**
     * Called with the error if the edit use case returns fail
     * @param error the response for the error
     */
    public LoginFail(String error){
        super(error);
    }
}
