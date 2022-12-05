import login_menu_entities.User;

import java.io.*;
import java.util.Scanner;

public class logInAction {

    /**
     * Logs the login_menu_entities.User into the system.
     * If the login_menu_entities.User enters the correct combination of name, password and type, the login_menu_entities.User logs in.
     * If the login_menu_entities.User enters a name that exists in the file but wrong password, the system displays wrong password.
     * If the login_menu_entities.User enters a name that does not exist in the file, the system creates a new login_menu_entities.User.
     * @param name name of the login_menu_entities.User
     * @param password password of the login_menu_entities.User
     */
    public static void logIn(String name, String password, String type) throws FileNotFoundException {
        // Create file if necessary
        try {
            File usersFile = new File("users.txt");
            if (usersFile.createNewFile()) {
                System.out.println("File created: " + usersFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(new File("users.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(name)) {
                String[] userInfo = line.split(", ");
                if (password.equals(userInfo[1])) {
                    if (type.equals(userInfo[2])){System.out.println("Successfully logged in!");}
                    else { System.out.println("You are not a " + type + ".");}
                } else {
                    System.out.println("Wrong password.");
                }
                return;
            }
        }
        // Create a new account
        try {
         //   User newUser = new User(name, password, type);
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
          //  writer.write(newUser.toString());
            writer.newLine();
            writer.close();
            System.out.println("New account created!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    /**
     * Changes the password of the login_menu_entities.User.
     * If the login_menu_entities.User's name exists in the file, the system changes the login_menu_entities.User's password to the given password.
     * Otherwise, the system displays that no such user exists.
     * @param user the login_menu_entities.User whose password is to be changed.
     * @param password the new password
     */
    public static void changePassword(User user, String password) throws Exception {

        // Checks if the user exists
        Scanner scanner1 = new Scanner(new File("users.txt"));
        boolean contains = false;
        while (scanner1.hasNextLine()) {
            String line = scanner1.nextLine();
            if (line.contains(user.getName())) {
                contains = true;
            }
        }
        if (! contains) {
            System.out.println("Username does not exist.");
        } else {
            // Changes the password of the user
            File usersFile = new File("users.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt", true));
            Scanner scanner = new Scanner(usersFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.contains(user.getName())) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    user.setPassword(password);
                    String newInfo = user.toString();
                    writer.write(newInfo);
                    writer.newLine();
                }
            }
            writer.close();
            File temp = new File("temp.txt");
            boolean success = temp.renameTo(usersFile);
            if (success) {
                System.out.println("Password updated");
            }

        }

    }
}
