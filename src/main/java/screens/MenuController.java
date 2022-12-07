package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Log Out")){
            System.out.println("(TEMP MESSAGE) Resets the game object, and displays the log in screen");
        } else if (e.getActionCommand().equals("Play")) {
            System.out.println("(TEMP MESSAGE) Changes the screen to the game screen (does not change any data)");
        }
        else if (e.getActionCommand().equals("Edit login_menu_entities.User")){
            System.out.println("(TEMP MESSAGE) edits the balance that the provided user has in the database");
        }
    }
}
