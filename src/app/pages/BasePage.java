package app.pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.Config;

/**
 * BasePage works like a base html
 */
public class BasePage extends Page {
    private Page content;

    /**
     * Makes a base page surounding the content
     * 
     * @param content the content
     */
    public BasePage (Page content) {
        this.content = content;
    }

    @Override
    public void createSwingUi() {
        // example: base ui

        // create the base page
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        // add header
        add(header(), BorderLayout.PAGE_START);

        // add content
        content.createSwingUi();
        add(content, BorderLayout.CENTER);
    }

    /**
     * Creates a header for the page
     * 
     * @return the header
     */
    private JPanel header() {
        // create header
        FlowLayout headerLayout = new FlowLayout();
        JPanel header = new JPanel(headerLayout);

        // create main menu button
        JButton button = new JButton();
        button.setBackground(Config.BACKGROUND_COLOR);
        button.setForeground(Config.FOREGROUND_COLOR);
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setText("return to main menu");
        button.addActionListener(event -> {
            PageManager manager = PageManager.getInstance();
            Page page = new MainPage();
            manager.setCurrentPage(page);
        });
        header.add(button);

        // get the current page name
        JLabel pageName = new JLabel(content.toString());
        header.add(pageName);

        return header;
    }

    @Override
    public void open() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'open'");
    }    
}
