package menu_use_case;

/**
 * This class is a presenter in clean architecture. It is used as input.
 */
public interface MenuPresenter {
    /**
     * This method will return a response model given the current response model
     * @param user the response model that will be passed into the function
     * @return the new response model
     */
    MenuResponseModel prepareSuccessView(MenuResponseModel user);
    /**
     * This method will throw an error given the error message
     * @param error the error message
     * @return should throw an error
     */
    MenuResponseModel prepareFailView(String error);
}
