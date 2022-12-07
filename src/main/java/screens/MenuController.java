package screens;

import menu_use_case.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Log Out")){

        } else if (e.getActionCommand().equals("Play")) {
            System.out.println("(TEMP MESSAGE) Changes the screen to the game screen (does not change any data)");
        }
        else if (e.getActionCommand().equals("Edit login_menu_entities.User")){

        }
    }
}
