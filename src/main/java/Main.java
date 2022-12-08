import javax.swing.*;

import admin_menu_use_case.AdminEditInteractor;
import admin_menu_use_case.AdminEditResponseFormatter;
import admin_menu_use_case.AdminFileChecker;
import login_menu_entities.UserFactory;
import login_menu_entities.UserInterfaceFactory;
import login_menu_use_casee.*;
import menu_use_case.*;
import register_menu_use_case.*;
import screens.*;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        boolean loggedIn = false;
        boolean inGame = false;
        boolean mainMenuInitiate = true;
        String usersfile = "src/main/users.txt";


        JFrame application = new JFrame("Gambler's Choice");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        UserLoginDSGateway user;
        try {
            user = new LoginFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserLoginPresenter presenter = new UserLoginResponseFormatter();
        UserInterfaceFactory userFactory = new UserFactory();
        UserLoginInputBoundary loginInputBoundary = new UserLoginInteractor(user, presenter, userFactory);
        LoginController loginController = new LoginController(loginInputBoundary);
        UserRegisterDSGateway user2;
        try {
            user2 = new RegisterFileChecker(usersfile);
        } catch (IOException e) {
            throw new RuntimeException("File could not be created");
        }
        UserRegisterPresenter presenter1 = new UserRegisterResponseFormatter();
        UserRegisterInputBoundary registerInputBoundary = new UserRegisterInteractor(user2, presenter1, userFactory);
        RegisterController registerController = new RegisterController(registerInputBoundary);

        LoginScreen loginScreen = new LoginScreen(application, loginController, registerController);

        AdminFileChecker adminFileChecker;
        try{
            adminFileChecker = new AdminFileChecker(usersfile);
        } catch (IOException e){
            throw new RuntimeException("File could not be created");
        }
        AdminEditResponseFormatter adminEditResponseFormatter = new AdminEditResponseFormatter();
        AdminEditInteractor adminEditInteractor = new AdminEditInteractor(adminFileChecker, adminEditResponseFormatter);
        AdminEditBalanceController adminEditBalanceController = new AdminEditBalanceController(adminEditInteractor);
        AdminMainMenu adminMenuScreen = new AdminMainMenu(application, adminEditBalanceController, loginScreen.getUser());

        MenuFileChecker menuUser;
        try {
            menuUser = new MenuFileChecker(usersfile);
        } catch (IOException e){
            throw new RuntimeException("File could not be created");
        }
        MenuResponseFormatter menuResponseFormatter = new MenuResponseFormatter();
        MenuInteractor menuInteractor = new MenuInteractor(menuUser, menuResponseFormatter);
        MenuController menuController = new MenuController(menuInteractor);
        MainMenu menuScreen = new MainMenu(application, menuController, loginScreen.getUser());;

        screens.add(loginScreen, "Login");
        application.pack();
        application.setSize(1000,800);
        application.setResizable(false);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);

        while (true) {
            if (!loggedIn)  {
                loggedIn = loginScreen.isLoggedIn();
                mainMenuInitiate = loggedIn;
                cardLayout.show(screens, "Login");
            } else if (loggedIn && !inGame) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    JOptionPane.showMessageDialog(application, "There was an error loading your account");
                }
                if (loginScreen.getType().equals("admin") && mainMenuInitiate){
                    adminMenuScreen = new AdminMainMenu(application, adminEditBalanceController, loginScreen.getUser());
                    screens.add(adminMenuScreen, "Menu");
                    mainMenuInitiate = false;
                } else if (loginScreen.getType().equals("user") && mainMenuInitiate) {
                    menuScreen = new MainMenu(application, menuController, loginScreen.getUser());
                    screens.add(menuScreen, "Menu");
                    mainMenuInitiate = false;
                }

                cardLayout.show(screens, "Menu");
                if (loginScreen.getType().equals("admin")) {
                    loggedIn = adminMenuScreen.isLoggedIn();
                    inGame = adminMenuScreen.isInGame();
                } else {
                    loggedIn = menuScreen.isLoggedIn();
                    inGame = menuScreen.isInGame();
                }
                if (!loggedIn) {
                    screens.remove(0);
                    loginScreen = new LoginScreen(application, loginController, registerController);
                    screens.add(loginScreen, "Login");
                    mainMenuInitiate = false;
                }

            } else if (inGame) {

            }
        }
    }

}
