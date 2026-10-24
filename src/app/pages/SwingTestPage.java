package app.pages;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Scanner;

import app.pages.context.Page;
import app.pages.context.PageManager;
import app.util.Config;
import app.util.ScannerSingleton;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BorderFactory;

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
        super();
    }


    @Override
    protected void createSwingUi() {
        // Set up the main panel
        CardLayout cardLayout = new CardLayout();
        setLayout(new GridLayout(1, 1));
        setBackground(Color.BLACK);

        JPanel menuPanel = new JPanel(cardLayout);
        menuPanel.setBackground(Color.BLACK);
        add(menuPanel);

        // Main menu
        JPanel mainMenu = new JPanel(new GridLayout(3, 1, 10, 10));
        mainMenu.setBackground(Color.BLACK);
        mainMenu.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(25, 40, 25, 40)
        );

        JLabel title = new JLabel("Tic-Tac-Toe Game", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        mainMenu.add(title);

// Local button
        JButton localButton = new JButton("Local");
        styleButton(localButton);
        mainMenu.add(localButton);

// Online button
        JButton onlineButton = new JButton("Online");
        styleButton(onlineButton);
        mainMenu.add(onlineButton);

        // Local menu
        JPanel localMenu = new JPanel(new GridLayout(4, 1, 10, 20));
        localMenu.setBackground(Color.BLACK);
        localMenu.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(25, 40, 25, 40)
        );


        JLabel localTitle = new JLabel("Local Game", JLabel.CENTER);
        localTitle.setFont(new Font("Arial", Font.BOLD, 36));
        localTitle.setForeground(Color.WHITE);

        JButton localAiButton = new JButton("Play vs AI");
        JButton localPlayerButton = new JButton("Play vs Local Player");
        JButton localBackButton = new JButton("Back");

        styleButton(localAiButton);
        styleButton(localPlayerButton);
        styleButton(localBackButton);


        localMenu.add(localTitle);
        localMenu.add(localAiButton);
        localMenu.add(localPlayerButton);
        localMenu.add(localBackButton);

        // Online menu
        JPanel onlineMenu = new JPanel(new GridLayout(4, 1, 10, 20));
        onlineMenu.setBackground(Color.BLACK);
        onlineMenu.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(25, 40, 25, 40)
        );

        JLabel onlineTitle = new JLabel("Online Game", JLabel.CENTER);
        onlineTitle.setFont(new Font("Arial", Font.BOLD, 36));
        onlineTitle.setForeground(Color.WHITE);

        JButton onlineAiButton = new JButton("Play vs AI");
        JButton onlinePlayerButton = new JButton("Play vs Online Player");
        JButton onlineBackButton = new JButton("Back");

        styleButton(onlineAiButton);
        styleButton(onlinePlayerButton);
        styleButton(onlineBackButton);

        onlineMenu.add(onlineTitle);
        onlineMenu.add(onlineAiButton);
        onlineMenu.add(onlinePlayerButton);
        onlineMenu.add(onlineBackButton);

        // Add menus to CardLayout
        menuPanel.add(mainMenu, "MAIN");
        menuPanel.add(localMenu, "LOCAL");
        menuPanel.add(onlineMenu, "ONLINE");

        // Navigation
        localButton.addActionListener(event ->
                cardLayout.show(menuPanel, "LOCAL")
        );

        onlineButton.addActionListener(event ->
                cardLayout.show(menuPanel, "ONLINE")
        );

        localBackButton.addActionListener(event ->
                cardLayout.show(menuPanel, "MAIN")
        );

        onlineBackButton.addActionListener(event ->
                cardLayout.show(menuPanel, "MAIN")
        );

        // Temporary actions until the game is connected
        localAiButton.addActionListener(event ->
                System.out.println("Local: Play vs AI")
        );

        localPlayerButton.addActionListener(event ->
                System.out.println("Local: Play vs Local Player")
        );

        onlineAiButton.addActionListener(event ->
                System.out.println("Online: Play vs AI")
        );

        onlinePlayerButton.addActionListener(event ->
                System.out.println("Online: Play vs Online Player")
        );
    }


    private void styleButton(JButton button) {
        button.setBackground(new Color(25, 25, 25));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setFocusPainted(false);
        button.setBorder(
                javax.swing.BorderFactory.createLineBorder(
                        new Color(120, 120, 120), 2
                )
        );
        button.setOpaque(true);
    }



    /**    (non-Javadoc)
     * @author code andy
     * 
     * Asks if the player wants to exit the test page
     * 
     * @see app.pages.context.Page#open()
     */
    @Override
    public void open() {
        // The Swing menu is already displayed.
        // No terminal input is needed.
    }

}
