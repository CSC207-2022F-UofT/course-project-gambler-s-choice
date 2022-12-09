package screens;

/**
 * Specific error for the admin edit use case
 */
public class EditFail extends RuntimeException{

    /**
     * Called with the error if the edit use case returns fail
     * @param error the response for the error
     */
    public EditFail(String error){
        super(error);
    }
}
