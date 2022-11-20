import javax.swing.*;

public interface Screen {

    /**
     * Load in the items in the background of each individual screen
     * @return The JPanel that contains all the required items in the background
     */
    public JPanel loadBackground();

    /**
     * Load in the buttons in the background of each individual screen
     * @return The JPanel that contains all the required buttons
     */
    public JPanel loadButtons();

    /**
     * Clear the "Screen" (current window) before creating the screen items
     * @param frame The JFrame window that is being used
     */
    public default void clearScreen(JFrame frame){
        frame.removeAll();
        frame.revalidate();
        frame.repaint();
    }
}
