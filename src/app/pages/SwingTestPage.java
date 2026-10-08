package app.pages;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Scanner;

import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.Config;
import app.util.ScannerSingleton;

/**
 * SwingTestPage
 */
public class SwingTestPage extends Page {
    @Override
    public String toString() {
        String toReturn = "swing test page";
        return toReturn;
    }

    /**
     * Constructor
     * 
     * Makes the page using swing
     */
    public SwingTestPage() {
        super();
    }

    @Override
    protected void createSwingUi() {
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setBackground(Config.BACKGROUND_COLOR);
            button.setForeground(Config.FOREGROUND_COLOR);
            button.setFont(new Font("Arial", Font.BOLD, 120));

            button.addActionListener(event -> {
                button.setText("X");
                button.setEnabled(false);
            });

            add(button);
        }
    }

    /**    (non-Javadoc)
     * @author code andy
     * 
     * Asks if the player wants to exit the test page
     * 
     * @see app.pages.context.Page#open()
     */
    @Override
    public void open() {
        System.out.println("press enter to exit");
        Scanner scanner = ScannerSingleton.getInstance();
        scanner.nextLine();

        Page page = new MainPage();
        PageManager manager = PageManager.getInstance();
        manager.setCurrentPage(page);
    }

}
