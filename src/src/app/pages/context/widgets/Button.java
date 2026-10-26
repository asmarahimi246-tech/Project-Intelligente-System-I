package src.app.pages.context.widgets;

import app.util.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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