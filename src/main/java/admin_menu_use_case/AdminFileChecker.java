package admin_menu_use_case;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AdminFileChecker implements AdminEditGateway {

    private final ArrayList<String[]> accounts = new ArrayList<String[]>();
    private final File usersFile;

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
        try{
            update();
        }catch (Exception e){
            return false;
        }
        for (String[] account: accounts){
            if (account[0].equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validBalance(String balance) {
        try{update();}catch (Exception e){return false;}
        try {
            Integer.parseInt(balance);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean sufficientBalance(String user) {
        try{
            update();
        }catch (Exception e){
            return false;
        }        for (String[] account: accounts){
            if (account[0].equals(user) && Integer.parseInt(account[3]) >= 100){
                return true;
            }
        }
        return false;
    }

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
        try{
            update();
        }catch (Exception e){
            return false;
        }        for (String[] account: accounts){
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

    /**
     * Private helper method that updates the accounts instance variable
     * @throws IOException
     */
    private void update() throws IOException{
        accounts.clear();
        Scanner scanner = new Scanner(usersFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] account = line.split(", ");
            accounts.add(account);
        }
        scanner.close();
    }

}
