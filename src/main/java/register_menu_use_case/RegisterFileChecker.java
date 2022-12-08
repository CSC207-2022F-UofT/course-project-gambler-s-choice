package register_menu_use_case;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RegisterFileChecker implements UserRegisterDSGateway{

    private final File usersFile;
    private final ArrayList<String[]> accounts = new ArrayList<String[]>();

    /**
     * Adds the account information into this RegisterFileChecker's accounts.
     * @param txtPath the name of the users information file
     */
    public RegisterFileChecker(String txtPath) throws IOException {
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
     * Checks if the given username exists in the database.
     * @param name the given username
     * @return true iff the given username exists in the database.
     */
    @Override
    public boolean existsByName(String name) {
        for (String[] account: accounts){
            if (account[0].equals(name)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the first given password matches with the second given password
     * @param pass1 the first given password
     * @param pass2 the second given password
     * @return true iff the first given password matches with the second given password
     */
    @Override
    public boolean matchingPass(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    /**
     * Adds the given username, password from the request model and the default type and initial balance into this RegisterFileChecker's accounts
     * @param requestModel a UserRegisterRequestModel
     */
    @Override
    public void save(UserRegisterRequestModel requestModel) {
        accounts.add(new String[] {requestModel.getUser(), requestModel.getPassword(), "user", "100"});
        this.save();
    }

    private void save(){
        String[] account = accounts.get(accounts.size() - 1);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(usersFile, true));
            writer.write(String.join(", ", account));
            writer.newLine();
            writer.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
