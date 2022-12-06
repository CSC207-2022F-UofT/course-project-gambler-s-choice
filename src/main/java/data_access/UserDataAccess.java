package data_access;

import entities.User;
import login_use_case.UserDataAccessInterface;

import java.io.*;
import java.util.Scanner;

public class UserDataAccess implements UserDataAccessInterface {

    boolean usernameBool = false;
    boolean passwordBool = false;
    boolean typeBool = false;

    @Override
    public boolean getPasswordBool(){
        return this.passwordBool;
    }

    @Override
    public boolean getUsernameBool() {
        return this.usernameBool;
    }

    @Override
    public boolean getTypeBool(){
        return this.typeBool;
    }

    @Override
    public void createFile(){
        try {
            File usersFile = new File("users.txt");
            if (usersFile.createNewFile()) {
                System.out.println("File created: " + usersFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void changeBoolean(User user) throws FileNotFoundException {

        String userName  = user.getName();
        String userPassword = user.getPassword();
        String userType = user.getType();

        Scanner scanner = new Scanner(new File("users.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(userName)) {
                String[] userInfo = line.split(", ");
                if (userPassword.equals(userInfo[1])) {
                    if (userType.equals(userInfo[2])){
                        // The User logs in successfully
                        this.usernameBool = true;
                        this.passwordBool = true;
                        this.typeBool = true;
                    }
                    else {
                        // The User entered the wrong type
                        this.usernameBool = true;
                        this.passwordBool = true;
                    }
                } else {
                    // The User entered the wrong password
                    this.usernameBool = true;
                }
            }
        }
    }

    /**
     * Create a new user and writes the user's information into the database.
     * @param user the given user
     */
    @Override
    public void createNewAccount(User user) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
            writer.write(user.toString());
            writer.newLine();
            writer.close();
            System.out.println("New account created.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
