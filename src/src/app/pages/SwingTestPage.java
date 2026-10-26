package src.app.pages;

import app.pages.MainPage;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.Config;
import app.util.ScannerSingleton;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * SwingTestPage
 */
public class SwingTestPage extends Page {
    @Override
    public String toString() {
        String toReturn = "swing test page";
        return toReturn;
    }

    @Override
    public void createSwingUi() {
        System.out.println("swing");
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

    @Override
    public void createCLI() {
        
        System.out.println("press enter to return to main menu");
        Scanner scanner = ScannerSingleton.getInstance();
        scanner.nextLine();

        Page page = new MainPage();
        PageManager manager = PageManager.getInstance();
        manager.setCurrentPage(page);
    }
}