package menu_use_case;

public interface MenuDSGateway {

    boolean sufficientBalance(String user);

    int getBalance(String user);
}
