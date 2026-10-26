package src.app.pages.context;

import app.util.Config;

import javax.swing.*;

/**
 * Page
 * 
 * This abstract class has all functions that a page must implement
 * 
 * It also allows the storing of any page as a Page and as a JPanel
 */
public abstract class Page extends JPanel {

    /**
     * Creates the swing ui
     */
    public abstract void createSwingUi();

    /**
     * Creates the swing ui
     */
    public abstract void createCLI();

    /** 
     * opens the page 
     * */
    public void open() {
        if (Config.SWING_SUPPORTED) {
            try {
                createSwingUi();
                revalidate();
                repaint();
                setVisible(true);
            } catch(UnsupportedOperationException e) {
                createCLI();
            }
        } else {
            createCLI();
        }
    }
}