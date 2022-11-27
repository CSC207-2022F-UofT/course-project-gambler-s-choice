package login_menu_entities;

public interface UserInterfaceFactory {
    UserInterface create(String name, String password, String type);
}
