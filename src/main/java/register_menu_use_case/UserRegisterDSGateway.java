package register_menu_use_case;

public interface UserRegisterDSGateway {
    /**
     * Checks if the given username exists in the database.
     * @param name the given username
     * @return true iff the given username exists in the database.
     */
    boolean existsByName(String name);
    /**
     * Checks if the first given password matches with the second given password
     * @param pass1 the first given password
     * @param pass2 the second given password
     * @return true iff the first given password matches with the second given password
     */
    boolean matchingPass(String pass1, String pass2);
    /**
     * Adds the given username, password from the request model and the default type and initial balance into this RegisterFileChecker's accounts
     * @param requestModel a UserRegisterRequestModel
     */
    void save(UserRegisterRequestModel requestModel);

}
