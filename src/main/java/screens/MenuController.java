package screens;

import menu_use_case.MenuInputBoundary;
import menu_use_case.MenuRequestModel;
import menu_use_case.MenuResponseModel;

/**
 * Controller used by the menu use case
 */
public class MenuController {

    final MenuInputBoundary userInput;

    /**
     * Constructor of the Controller which initializes the necessary gateway
     * @param menuGateway The input boundary for Menu Screen
     */
    public MenuController(MenuInputBoundary menuGateway){
        this.userInput = menuGateway;
    }

    /**
     * Creates the required response for the action depending on the input
     * @param user The username of the user provided by the login from before
     * @param input What button they press
     * @param rulesVisible Whether the rules on the menuscreen are visible or not
     * @return The response model that updates the file and returns the necessary response
     */
    MenuResponseModel create(String user, String input, boolean rulesVisible){
        MenuRequestModel requestModel = new MenuRequestModel(user, input, rulesVisible);

        return userInput.create(requestModel);
    }

}
