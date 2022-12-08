package menu_use_case;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuFileChecker implements MenuDSGateway{

    private final ArrayList<String[]> accounts = new ArrayList<String[]>();

    public MenuFileChecker(String txtPath) throws IOException{
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
    public boolean sufficientBalance(String user) {
        for (String[] account: accounts){
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
}
