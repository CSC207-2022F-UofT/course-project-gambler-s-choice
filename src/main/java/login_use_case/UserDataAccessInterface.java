package login_use_case;

import entities.User;

import java.io.FileNotFoundException;

public interface UserDataAccessInterface {

    boolean getUsernameBool();

    boolean getPasswordBool();

    boolean getTypeBool();

    void createFile();

    /**
     * Changes usernameBool, passwordBool, typeBool based on whether the username, password and type matches those
     * that exist in the database.
     *
     * @param user the given user
     * @throws FileNotFoundException
     */
    public void changeBoolean(User user) throws FileNotFoundException;

    /**
     * Create a new account and save the information into the database.
     * @param user the given user
     */
    public void createNewAccount(User user);

}
