package login_menu_entities;

public class User implements UserInterface{
    private final String name;
    private final String password;
    private final String type;
    private int balance = 100;

    /**
     * Creates a login_menu_entities.User. The initial balance is 100.
     * @param name name of the login_menu_entities.User
     * @param password password of the login_menu_entities.User
     * @param type type of this login_menu_entities.User (Admin or login_menu_entities.User)
     */
    public User(String name, String password, String type, int balance) {
        this.name = name;
        this.type = type;
        this.password = password;
        this.balance = balance;
    }
    public User(String name, String password, int balance) {
        this.name = name;
        this.password = password;
        this.type = "user";
        this.balance = balance;
    }
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
