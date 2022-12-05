import login_menu_entities.User;
import org.junit.jupiter.api.Test;

class AdminTest {

    @Test
    void editBalance(){
        Admin testAdmin = new Admin("aa", "bb");
        User testUser = new User("cc", "dd");

        testAdmin.editBalance(testUser, 100);
        assert testUser.getBalance() == 200;
    }
}
