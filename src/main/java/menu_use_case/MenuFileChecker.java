package menu_use_case;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuFileChecker implements MenuDSGateway{

    private final ArrayList<String[]> accounts = new ArrayList<String[]>();

    private final File usersFile;

    /**
     * Adds the account information into this MenuFileChecker's accounts.
     * @param txtPath the name of the users information file
     */
    public MenuFileChecker(String txtPath) throws IOException{
        usersFile = new File(txtPath);

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
     * Return whether the account has sufficient balance
     * @param user the given user
     * @return true iff the account has sufficient balance
     */
    @Override
    public boolean sufficientBalance(String user) {
        try{
            update();
        }catch (Exception e){
            return false;
        }

        for (String[] account: accounts){
            if (account[0].equals(user) && Integer.parseInt(account[3]) >= 100){
                return true;
            }
        }
        return false;
    }

    /**
     * Reports the balance of the given user
     * @param user the given user
     * @return the balance of the given user
     */
    @Override
    public int getBalance(String user) {
        try{
            update();
        }catch (Exception e){
            e.printStackTrace();
        }

        for (String[] account: accounts){
            if (account[0].equals(user)){
                return Integer.parseInt(account[3]);
            }
        }
        return 0;
    }


    /**
     * Private helper method that updates the accounts instance variable
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
