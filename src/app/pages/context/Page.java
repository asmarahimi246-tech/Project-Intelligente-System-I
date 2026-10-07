package app.pages.context;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Page
 * 
 * This abstract class has all functions that a page must implement
 * 
 * It also allows the storing of any page as a Page and as a JPanel
 */
public abstract class Page extends JPanel {
    /**
     * Constructor
     */
    protected Page () {
        createSwingUi();
    }

    protected void createSwingUi () {
        add(new JLabel("UI not implemented"));
    }

    /** 
     * opens the page 
     * */
    public abstract void open();
}
