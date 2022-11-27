package login_menu_entities;

public class UserFactory implements UserInterfaceFactory{
    @Override
    public User create(String name, String password, String type){
        if (type.equals("admin")){
            return new Admin(name, password);
        }
        else{
            return new User(name, password);
        }
    }
}
