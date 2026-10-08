package app.pages;


import java.util.Scanner;

import javax.swing.JLabel;

import app.App;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.pages.gameBuilder.GameBuilderPage;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * MainPage
 * 
 * The main menu
 */
public class MainPage extends Page {
    @Override
    public String toString() {
        String toReturn = "main page";
        return toReturn;
    }

    /**
     * Constructor
     */
    public MainPage () {
        super();
        add(new JLabel(toString()));
    }

    @Override
    public void open() {
        // print
        System.out.println("=== Main Menu ===");
        System.out.println("1. Play Game");
        System.out.println("2. Test swing");
        System.out.println("3. Exit application");
        System.out.print("Enter your choice: ");

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(3)
            .min(1)
            .build();
        int choice = field.getInput();

        // procces input
        PageManager manager = PageManager.getInstance();
        switch (choice) {
            case 1:
                Page localGame = new GameBuilderPage();
                manager.setCurrentPage(localGame);
                break;

            case 2:
                Page swingTest = new SwingTestPage();
                manager.setCurrentPage(swingTest);
                break;

            case 3:
                App.close();
                break;
        
            default:
                break;
        }
    }
}
