package screens;

import javax.swing.*;
import java.awt.*;

public class ErrorPanel extends JFrame{

    public ErrorPanel(String error){
        super(error);
        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
