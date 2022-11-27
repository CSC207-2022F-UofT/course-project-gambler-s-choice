package login_menu_entities;

public class User implements UserInterface {

    private String name;
    private String password;
    private final String type;
    private static int balance = 100;


    /**
     * Creates a User. The initial balance is 100.
     * @param name name of the User
     * @param password password of the User
     * @param type type of this User (Admin or User)
     */
    public User(String name, String password, String type) {
        this.name = name;
        this.type = type;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.type = "user";
        this.password = password;
    }

    /**
     * Reports the name of this User
     * @return name of this User
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Reports the password of this User
     * @return password of this User
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Reports the type of this User
     * @return password of this User
     */
    @Override
    public String getType(){
        return this.type;
    }

    /**
     * Reports the balance of this User.
     * @return balance in this account
     */
    @Override
    public int getBalance() {
        return balance;
    }
}
