package app.uiPages;

import java.util.ArrayList;
import java.util.Scanner;

import app.App;
import app.uiPages.context.Page;
import app.uiPages.context.PageManager;
import app.uiPages.context.widgets.Button;
import app.uiPages.context.widgets.OptionMenu;
import app.uiPages.gameBuilder.GameSelectionPage;
import app.util.Config;
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
        // backgound
        setBackground(Config.BACKGROUND_COLOR);

        // make buttons
        ArrayList<Button> options = new ArrayList<Button>();

        // manager
        PageManager manager = PageManager.getInstance();

        // game builder button
        Page gameBuilder = new BasePage(new GameSelectionPage());
        Button gameBuilderButton = new Button(event -> {manager.setCurrentPage(gameBuilder);}, "Game Selection");
        options.add(gameBuilderButton);

        // exit button
        Button exitButton = new Button(event -> {App.close();}, "Exit");
        options.add(exitButton);

        // make menu
        OptionMenu menu = new OptionMenu(options, "=== Main Menu ===");
        add(menu);
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
            .max(2)
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
                App.close();
                break;
        
            default:
                break;
        }
    }
}
