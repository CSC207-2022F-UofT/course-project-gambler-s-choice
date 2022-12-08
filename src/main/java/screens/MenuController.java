package screens;

import menu_use_case.MenuInputBoundary;
import menu_use_case.MenuRequestModel;
import menu_use_case.MenuResponseModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {

    final MenuInputBoundary userInput;

    public MenuController(MenuInputBoundary menuGateway){
        this.userInput = menuGateway;
    }

    MenuResponseModel create(String user, String input, boolean rulesVisible){
        MenuRequestModel requestModel = new MenuRequestModel(user, input, rulesVisible);

        return userInput.create(requestModel);
    }

}
