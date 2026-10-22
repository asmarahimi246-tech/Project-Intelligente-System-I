package app.pages.gameBuilder;

import java.awt.Font;

import javax.swing.JButton;

import app.pages.BasePage;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.Config;

/**
 * BasePage works like a base html
 */
public class BuilderBasePage extends BasePage {
    private Page previous;

    /**
     * Makes a base page surounding the content
     * 
     * @param content the content
     */
    public BuilderBasePage (Page content, Page previous) {
        super(content);
        this.previous = previous;
    }

    @Override
    protected JButton leftButton() {
        // create prev menu button
        JButton button = new JButton();
        button.setBackground(Config.BACKGROUND_COLOR);
        button.setForeground(Config.FOREGROUND_COLOR);
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setText("back");
        button.addActionListener(event -> {
            PageManager manager = PageManager.getInstance();
            manager.setCurrentPage(previous);
        });
        return button;
    }
}
