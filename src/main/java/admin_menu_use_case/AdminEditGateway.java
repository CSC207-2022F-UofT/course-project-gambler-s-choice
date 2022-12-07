package admin_menu_use_case;

public interface AdminEditGateway {
    boolean existsByName(String name);

     boolean editByName(String name, int balance);
}
