package app.pages.context;

import javax.swing.JPanel;

import app.util.Config;

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
        System.out.println("hello");
        if (Config.SWING_SUPPORTED) {
            try {
                createSwingUi();
                revalidate();
                repaint();
            } catch(UnsupportedOperationException e) {
                createCLI();
            }
        } else {
            System.out.println("hello2");
            createCLI();
        }
    }
}
