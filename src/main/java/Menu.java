import javax.swing.*;

public class Menu {

    public void runLogin(){

    }

    public void runHome(){

    }

    public void runGame(){

    }
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });

    }
}
