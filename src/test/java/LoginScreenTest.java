import javax.swing.*;

public class LoginScreenTest {

    public static void main(String[] args){

        JFrame aaaa = new JFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen(aaaa);
            }
        });

    }
}
