// Admin class
public class Admin extends User{

    public Admin(){

    }

    public void editBalance(User user, float amount){
        user.addBalance(amount);
    }

    //public void removePlayer(Game game, User user){

    //}
}
