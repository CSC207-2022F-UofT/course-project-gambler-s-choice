package admin_menu_use_case;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminFileChecker implements AdminEditGateway {

    private final ArrayList<String[]> accounts = new ArrayList<String[]>();
    private final File usersFile;

    /**
     * Adds the account information into this AdminFileCheck's accounts.
     * @param txtPath the name of the users information file
     */
    public AdminFileChecker(String txtPath) throws IOException{
        usersFile = new File(txtPath); //creates a File instance

        Scanner scanner = new Scanner(usersFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] account = line.split(", ");
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
     * Returns whether the balance is valid
     * @param balance the balance of the user
     * @return true iff the balance is valid
     */
    @Override
    public boolean validBalance(String balance) {
        try {
            Integer.parseInt(balance);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * Return whether the account has sufficient balance
     * @param user the given user
     * @return true iff the account has sufficient balance
     */
    @Override
    public boolean sufficientBalance(String user) {
        for (String[] account: accounts){
            if (account[0].equals(user) && Integer.parseInt(account[3]) >= 100){
                return true;
            }
        }
        return false;
    }

    /**
     * Reports the balance of the use
     * @param user the given user
     * @return the balance of the given user
     */
    @Override
    public int getBalance(String user) {
        for (String[] account: accounts){
            if (account[0].equals(user)){
                Integer.parseInt(account[3]);
            }
        }
        return 0;
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
                account[3] = Integer.toString(balance);


                try {
                    BufferedWriter writer;
                    writer = new BufferedWriter(new FileWriter(usersFile, false));
                    for (String[] acc : accounts) {

                        writer.write(String.join(", ", acc));

                        writer.newLine();
                    }
                    writer.close();
                }
                catch (Exception e){
                    return false;
                }
                return true;
            }
        }

        return false;
    }



}
