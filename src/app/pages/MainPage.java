package app.pages;

import java.util.Scanner;

import javax.swing.JLabel;

import app.App;
import app.pages.context.Page;
import app.pages.context.PageManager;
import app.pages.gameBuilder.GameSelectionPage;
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

    @Override
    public void createSwingUi() {
        // TODO: add ui
        throw new UnsupportedOperationException("UI not implemented");
    }

    @Override
    public void createCLI() {
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
                Page gameBuilder = new BasePage(new GameSelectionPage());
                manager.setCurrentPage(gameBuilder);
                break;

            case 2:
                Page swingTest = new BasePage(new SwingTestPage());
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
