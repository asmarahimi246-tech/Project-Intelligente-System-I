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
        if (Config.SWING_SUPPORTED) {
            try {
                System.out.println("swing ui being made");
                createSwingUi();
                revalidate();
                repaint();
            } catch(UnsupportedOperationException e) {
                System.out.println("cli being made");
                createCLI();
            }
        } else {
            createCLI();
        }
    }
}
