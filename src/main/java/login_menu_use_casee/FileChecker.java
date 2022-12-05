package login_menu_use_casee;

import register_menu_use_case.UserRegisterRequestModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileChecker implements UserLoginDSGateway{

    private final ArrayList<String[]> accounts = new ArrayList<String[]>();
    private final String[] account = new String[4];

    public FileChecker(String txtPath) throws IOException{
        File usersFile = new File(txtPath);

        Scanner scanner = new Scanner(usersFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] account = line.split(",");
            accounts.add(account);
        }
        scanner.close();
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
        return new String[]{account[3], account[4]};
    }
}
