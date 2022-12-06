package login_use_case;

public class UserResponseModel {
    String message;

    public UserResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String newMessage) {
        this.message = message;
    }
}
