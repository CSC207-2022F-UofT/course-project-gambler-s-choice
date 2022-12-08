package deprecated;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Deprecated
public class MainFrame extends JFrame{

    private TextPanel textPanel;
    private JButton btn;
    public MainFrame(){
        super("Gambler's Choice");

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        btn = new JButton("Login");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPanel.appendText("asdasd\n");
            }
        });

        add(textPanel, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);

        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
