package app.pages.gameBuilder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.pages.context.Page;
import app.pages.context.PageManager;
import app.pages.context.widgets.Button;

/**
 * BasePage works like a base html
 */
public class BuilderBasePage extends Page {
    private Page previous;
    private Page content;

    /**
     * Makes a base page surounding the content
     * 
     * @param content the content
     */
    public BuilderBasePage (Page content, Page previous) {
        this.content = content;
        this.previous = previous;
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

        // add the button
        Button button = new Button(event -> {
            PageManager manager = PageManager.getInstance();
            manager.setCurrentPage(previous);
            });
        header.add(button);

        // get the current page name
        JLabel pageName = new JLabel(content.toString());
        header.add(pageName);

        return header;
    }

    @Override
    public void createCLI() {
        // i am very usefull
    }

    @Override
    public void open() {
        // build this view first
        super.open();

        // then build the content view
        content.open();
    }
}
