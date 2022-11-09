package tutorial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

public class GameController implements ActionListener{
    public GameController(GameDisplay game){
        JPanel gameWindowMenu =  (JPanel) game.getContentPane().getComponent(1);
        for (int x = 0; x < gameWindowMenu.getComponentCount(); x++) {
            if (gameWindowMenu.getComponent(x) instanceof JButton) {
                JButton button = (JButton) gameWindowMenu.getComponent(x);
                button.addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Game.printInput(e.getActionCommand());
    }
}
