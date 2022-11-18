import javax.swing.*;

public interface Screen {

    public JPanel loadButtons();

    public default void clearScreen(JFrame frame){
        frame.removeAll();
        frame.revalidate();
        frame.repaint();
    }
}
