package app.uiPages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import app.uiPages.context.Page;
import app.util.Config;

/**
 * ClosingPage
 * 
 * The last page you see before the application closses
 */
public class LoadingPage extends Page {
    String text;

    public LoadingPage(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "closing page";
    }

    @Override
    public void createSwingUi() {
        setBackground(Config.BACKGROUND_COLOR);
        setLayout(new GridBagLayout());

        // content pane
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Config.BACKGROUND_COLOR);

        // closing message
        JLabel message = new JLabel(text, SwingConstants.CENTER);
        message.setFont(Config.MEDIUM_FONT);
        message.setForeground(Config.FOREGROUND_COLOR);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        // progress bar
        JProgressBar progress = new JProgressBar();
        progress.setIndeterminate(true);
        progress.setPreferredSize(new Dimension(250, 18));
        progress.setMaximumSize(new Dimension(250, 18));
        progress.setAlignmentX(Component.CENTER_ALIGNMENT);
        progress.setBorderPainted(false);

        // add both to content with spacing
        content.add(message);
        content.add(Box.createVerticalStrut(16));
        content.add(progress);

        add(content);
    }

    @Override
    public void createCLI() {
        System.out.println(text);
    }
    
    @Override
    public void open() {
        // build this view first
        super.open();

        if (Config.DEBUG) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
