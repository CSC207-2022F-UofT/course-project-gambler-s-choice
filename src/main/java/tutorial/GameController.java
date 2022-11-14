package tutorial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    JTextField textObj;

    public GameController(JTextField text) {
        super();
        this.textObj = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Bet")) {
            System.out.println(this.textObj.getText());
        }
    }
}
