package login_menu_entities;

public class UserFactory implements UserInterfaceFactory{

    /**
     * Creates a new User or admin with the given information.
     * @param name the name of the given user
     * @param password the password of the given user
     * @param type the type of the given user
     * @param balance the balance of the given user
     * @return a new User or Admin with the given information
     */
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

