import javax.swing.*;

public class AdminMenuScreenTest {
    public static void main(String[] args){

        JFrame testFrame = new JFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen(testFrame);
            }
        });

    }
}
