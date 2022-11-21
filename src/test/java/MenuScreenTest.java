import javax.swing.*;

public class MenuScreenTest {
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
