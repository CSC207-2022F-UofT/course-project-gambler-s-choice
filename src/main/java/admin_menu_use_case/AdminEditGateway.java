package admin_menu_use_case;

import java.io.IOException;

public interface AdminEditGateway {
    boolean existsByName(String name);

     boolean editByName(String txtPath, String name, int balance);
}
