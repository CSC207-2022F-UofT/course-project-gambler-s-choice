package entities;

public class User {
    private String name;
    private String password;
    private final String type;
    private static int balance = 100;

    /**
     * Creates a entities.User. The initial balance is 100.
     * @param name name of the entities.User
     * @param password password of the entities.User
     * @param type type of this entities.User (Admin or entities.User)
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
     * Reports the name of this entities.User
     * @return name of this entities.User
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this entities.User
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Reports the password of this entities.User
     * @return password of this entities.User
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Reports the password of this entities.User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Reports the type of this entities.User
     * @return password of this entities.User
     */
    public String getType(){
        return this.type;
    }

    /**
     * Reports the balance of this entities.User.
     * @return balance in this account
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adds the given amount to the balance of this entities.User.
     */
    public void addBalance(int amount){
        balance += amount;
    }

    /**
     * Reports the information of this User.
     * @return the information of this User.
     */
    @Override
    public String toString(){
        return this.name + ", " + this.password + ", " + this.type + ", " + balance;
    }

}
