package login_menu_use_casee;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginFileChecker implements UserLoginDSGateway{

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


    @Override
    public boolean existsByName(String name) {
        for (String[] account: accounts){
            if (account[0].equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean matchingPass(String pass) {
        for (String[] account: accounts){
            if (account[1].equals(pass)){
                return true;
            }
        }
        return false;
    }

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
