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
     */
    public Button(ActionListener onPress) {
        setBackground(Config.BACKGROUND_COLOR);
        setForeground(Config.FOREGROUND_COLOR);
        setFont(new Font("Arial", Font.BOLD, 10));
        setText("back");
        addActionListener(onPress);
    }
}
