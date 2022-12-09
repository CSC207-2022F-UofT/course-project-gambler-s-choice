package screens;

import javax.swing.*;

/**
 * Screen interface since some screens have overlapping design elements
 */
public interface Screen {

    /**
     * Load in the items in the background of each individual screen
     * @return The JPanel that contains all the required items in the background
     */
    JPanel loadBackground();

    /**
     * Load in the buttons in the background of each individual screen
     * @return The JPanel that contains all the required buttons
     */
    JPanel loadButtons();
}
