package app.pages.context.widgets;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import app.util.Config;

/**
 * Button
 */
public class Button extends JButton {

    /**
     * A button that executes a function when pressed
     * 
     * @param onPress the function to execute
     * @param text the text to place on the button
     */
    public Button(ActionListener onPress, String text) {
        styleButton();
        setText(text);
        addActionListener(onPress);
    }


    /**
     * Sets the button style
     */
    private void styleButton() {
        setBackground(Config.BACKGROUND_COLOR);
        setForeground(Config.FOREGROUND_COLOR);
        setFont(Config.MEDIUM_FONT);
        setFocusPainted(false);
        setBorder(Config.BORDER);
        setOpaque(true);
    }
}
