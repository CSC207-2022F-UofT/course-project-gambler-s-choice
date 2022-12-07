package menu_use_case;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminFileChecker implements AdminEditGateway {

    private final ArrayList<String[]> accounts = new ArrayList<String[]>();
    private final String[] account = new String[2]; //No need to edit an account, we just need the username and the new balance to set it to

    public AdminFileChecker(String txtPath) throws IOException{
        File usersFile = new File(txtPath); //creates a File instance

        Scanner scanner = new Scanner(usersFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] account = line.split(",");
            accounts.add(account);
        }
        scanner.close();
    }

    /**
     * This method checks whether the account specified by the name exists and returns a boolean dependent on that fact.
     * @param name username that we're checking exists
     * @return whether the account exists
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
     * Edits the account given by the name of the user. If the user does not exist, returns false
     * @param name Name of the user we need to edit
     * @param balance balance that we want to set the user to
     * @return whether the user exists and the balance is set
     */

    @Override
    public boolean editByName(String name, int balance) {
        for (String[] account: accounts){
            if (account[0].equals(name)){
                account[4] = Integer.toString(balance);
                return true;
            }

            }
        return false;
    }


}
