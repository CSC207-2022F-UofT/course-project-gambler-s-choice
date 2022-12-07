import javax.swing.*;

import login_menu_entities.UserFactory;
import login_menu_entities.UserInterfaceFactory;
import login_menu_use_casee.*;
import register_menu_use_case.*;
import screens.*;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Gambler's Choice");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        UserLoginDSGateway user;
        try {
            user = new LoginFileChecker("src/main/users.txt");
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserLoginPresenter presenter = new UserLoginResponseFormatter();
        UserInterfaceFactory userFactory = new UserFactory();
        UserLoginInputBoundary inputBoundary = new UserLoginInteractor(user, presenter, userFactory);
        LoginController controller = new LoginController(inputBoundary);
        UserRegisterDSGateway user2;
        try {
            user2 = new RegisterFileChecker("src/main/users.txt");
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserRegisterPresenter presenter1 = new UserRegisterResponseFormatter();
        UserRegisterInputBoundary inputBoundary1 = new UserRegisterInteractor(user2, presenter1, userFactory);
        RegisterController controller1 = new RegisterController(inputBoundary1);

        LoginScreen loginScreen = new LoginScreen(application, controller, controller1);


        MainMenu menuScreen = new MainMenu(application);

        screens.add(loginScreen, "Login");
        screens.add(menuScreen, "Menu");
        application.pack();
        application.setSize(1000,800);
        application.setResizable(false);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);

        while (true) {
            if (!loginScreen.isLoggedIn()) {
                cardLayout.show(screens, "Login");
            } else if (loginScreen.isLoggedIn()) {
                cardLayout.show(screens, "Menu");
            }
        }
    }

}
