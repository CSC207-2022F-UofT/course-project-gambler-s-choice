package login_menu_use_casee;

import register_menu_use_case.UserRegisterRequestModel;

public interface UserLoginDSGateway {
    boolean existsByName(String name);

    boolean matchingPass(String user, String pass);

    String[] getAccountInfo(String name, String pass);
}
