package admin_menu_use_case;

/**
 * an interface for the application to use
 * AdminFileChecker implements this
 */
public interface AdminEditGateway {

    boolean existsByName(String name);

    boolean validBalance(String balance);
    boolean sufficientBalance(String user);

    int getBalance(String user);
    boolean editByName(String name, int balance);
}
