package login_menu_entities;

public class UserFactory implements UserInterfaceFactory{
    @Override
    public UserInterface create(String name, String password, String type, int balance){
        if (type.equals("admin")){
            if (balance == -1){
                return new Admin(name, password);
            } else {
                return new Admin(name, password, balance);
            }

        }
        else{
            if (balance == -1){
                return new User(name, password);
            } else {
                return new User(name, password, balance);
            }
        }
    }
}

