package app.pages;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import app.pages.context.Page;

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
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.WHITE);
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
     * Makes a swing test page
     * 
     * @see app.pages.context.Page#open()
     */
    @Override
    public void open() {
    }

}
