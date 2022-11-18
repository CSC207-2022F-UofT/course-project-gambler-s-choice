//TEMPORARY FILE WILL BE OVERWRITTEN BY ACTUAL USER CLASS

public class User {
    private float balance;
    private String user;
    private String password;

    public User(){
        this.user = null;
        this.password = null;
    }

    public User(String user, String password){
        this.user = user;
        this.password = password;
    }

    public float getBalance(){
        return balance;
    }

    public void addBalance(float add){
        balance += add;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public String getPassword(){
        return password;
    }

    public void joinGame(){

    }
}
