package login_menu_entities;

/**
 * User Entity that stores the data of the User
 */
public class User implements UserInterface{
    private final String name;
    private final String password;
    private final String type;
    private int balance = 100;

    /**
     * Creates a User with a preset type and balance
     * @param name name of the User
     * @param password password of the User
     * @param type type of this User (Admin or User)
     */
    public User(String name, String password, String type, int balance) {
        this.name = name;
        this.type = type;
        this.password = password;
        this.balance = balance;
    }

    /**
     * Creates a User with a user type and preset balance
     * @param name name of the User
     * @param password password of the User's account
     * @param balance balance of the User's account
     */
    public User(String name, String password, int balance) {
        this.name = name;
        this.password = password;
        this.type = "user";
        this.balance = balance;
    }

    /**
     * Creates a User with default balance
     * @param name name of the User
     * @param password password of the User's account
     * @param type type of this User
     */
    public User(String name, String password, String type) {
        this.name = name;
        this.type = type;
        this.password = password;
    }

    /**
     * Creates a default User with default balance
     * @param name name of the User
     * @param password password of the User's account
     */
    public User(String name, String password) {
        this.name = name;
        this.type = "user";
        this.password = password;
    }

    /**
     * Reports the name of this login_menu_entities.User
     * @return name of this login_menu_entities.User
     */
    public String getName() {
        return this.name;
    }

    /**
     * Reports the password of this login_menu_entities.User
     * @return password of this login_menu_entities.User
     */
    public String getPassword() {
        return this.password;
    }


    /**
     * Reports the type of this login_menu_entities.User
     * @return password of this login_menu_entities.User
     */
    public String getType(){
        return this.type;
    }

    /**
     * Reports the balance of this login_menu_entities.User.
     * @return balance in this account
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adds the given amount to the balance of this login_menu_entities.User.
     */
    public void addBalance(int amount){
        balance += amount;
    }

    @Override
    public String toString(){
        return this.name + ", " + this.password + ", " + this.type + ", " + balance;
    }

}
