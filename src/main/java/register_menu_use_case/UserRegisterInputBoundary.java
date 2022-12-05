package register_menu_use_case;

public interface UserRegisterInputBoundary {
    UserRegisterResponseModel create(UserRegisterRequestModel requestModel);
}
