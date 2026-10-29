package app.uiPages.context.widgets;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.util.Config;

/**
 * OptionMenu
 * 
 * Has buttons with options that you can select
 */
public class OptionMenu extends JPanel {
    /**
     * Makes an option menu
     * 
     * @param options the options to choose from
     * @param title the title of the menu
     */
    public OptionMenu (ArrayList<Button> options, String title) {
        // set up main panel
        setLayout(new GridLayout(options.size()+1, 1, 10, 10));
        setBackground(Config.BACKGROUND_COLOR);
        setBorder(Config.BORDER);

        // add title
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setForeground(Config.FOREGROUND_COLOR);
        titleLabel.setFont(Config.MEDIUM_FONT);
        add(titleLabel);

        // add buttons
        for (Button toAdd : options) {
            add(toAdd);
        }
    }
}
