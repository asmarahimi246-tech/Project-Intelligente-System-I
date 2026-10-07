package app.pages;


import java.util.Scanner;

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

    @Override
    public void open() {
        // print
        System.out.println("=== Tic Tac Toe ===");
        System.out.println("1. Local game");
        System.out.println("2. Online game");
        System.out.println("3. Test swing");
        System.out.println("4. Exit application");
        System.out.print("Enter your choice: ");

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(4)
            .min(1)
            .build();
        int choice = field.getInput();

        // procces input
        PageManager manager = PageManager.getInstance();
        switch (choice) {
            case 1:
                Page localGame = new GameBuilderPage("local");
                manager.setCurrentPage(localGame);
                break;

            case 2:
                Page onlineGame = new GameBuilderPage("online");
                manager.setCurrentPage(onlineGame);
                break;

            case 3:
                Page swingTest = new SwingTestPage();
                manager.setCurrentPage(swingTest);
                break;

            case 4:
                break;
        
            default:
                break;
        }
    }
}
