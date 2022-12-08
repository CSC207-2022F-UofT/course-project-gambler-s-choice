package login_menu_use_casee;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginFileChecker implements UserLoginDSGateway{

    /**
     * Adds the account information into this LoginFileCheck's accounts.
     * @param txtPath the name of the users information file
     */
    private final ArrayList<String[]> accounts = new ArrayList<String[]>();

    public LoginFileChecker(String txtPath) throws IOException{
        File usersFile = new File(txtPath);

        if (!(usersFile.createNewFile())) {
            Scanner scanner = new Scanner(usersFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] account = line.split(", ");
                accounts.add(account);
            }
            scanner.close();
        }
    }

    /**
     * Checks if the given username exists in the database.
     * @param name the given username
     * @return true iff the given username exists in the database.
     */
    @Override
    public boolean existsByName(String name) {
        for (String[] account: accounts){
            if (account[0].equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given password matches with the password corresponding to the username in the database
     * @param user the given username
     * @param pass the given password
     * @return true iff the given password matches with the password corresponding to the username in the database
     */
    @Override
    public boolean matchingPass(String user, String pass) {
        for (String[] account: accounts){
            if (account[0].equals(user) && account[1].equals(pass)){
                return true;
            }
        }
        return false;
    }

    /**
     * Reports the type and balance of the user with the given name and password
     * @param name the given name
     * @param pass the given password
     * @return the type and balance of the user with the given name and password
     */
    @Override
    public String[] getAccountInfo(String name, String pass) {
        String[] accountInfo = new String[2];
        for (String[] account: accounts){
            if (account[0].equals(name) && account[1].equals(pass)) {
                accountInfo[0] = account[2];
                accountInfo[1] = account[3];
            }
        }
        return accountInfo;
    }

}
