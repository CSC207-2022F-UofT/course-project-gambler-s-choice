package deprecated_tests;

import screens.LoginScreen;

import javax.swing.*;

@Deprecated
public class LoginScreenTest {

    public static void main(String[] args){

        JFrame aaaa = new JFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
         //       new LoginScreen(aaaa);
            }
        });

    }
}
