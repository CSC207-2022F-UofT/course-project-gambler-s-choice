package register_menu_use_case;

public interface UserRegisterDSGateway {
    boolean existsByName(String name);
    boolean matchingPass(String pass1, String pass2);

    void save(UserRegisterRequestModel requestModel);

}
