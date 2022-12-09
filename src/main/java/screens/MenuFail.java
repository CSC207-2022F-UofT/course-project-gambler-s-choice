package screens;

/**
 * Specific error for the menu use case
 */
public class MenuFail extends RuntimeException{

    /**
     * Called with the error if the edit use case returns fail
     * @param error the response for the error
     */
    public MenuFail(String error){
        super(error);
    }
}
