package app.pages.gameBuilder;

import java.util.Scanner;

import javax.swing.JPanel;

import app.pages.context.Page;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * GameBuilderPage
 * 
 * Contains the ui for building a game
 */
public class GameBuilderPage extends Page {
    private String gameMode;

    /**
     * Makes a game builder with the game mode
     * 
     * @param gameMode on of the following:
     *  online
     *  local
     *  tournament
     */
    public GameBuilderPage(String gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public void open() {
        boolean aiMode = aiSubMenu();
    }
    
    /**
     * Submenu for choosing an opponent
     * 
     * @return true if the  opponent is an ai
     */
    private boolean aiSubMenu() {
        boolean toReturn = false;

        // print
        switch (gameMode) {
            case "online":
                System.out.println("===Online Game===");
                break;
            
            case "local":
                System.out.println("===Local Game===");
                break;
        
            case "tournament":
                System.out.println("===Tournament Game===");
                break;
        
            default:
                break;
        }

        System.out.println("1. Player");
        System.out.println("2. AI");
        System.out.print("Enter your choice: ");

        // get input
        Scanner scanner = ScannerSingleton.getInstance();
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "Something went wrong.")
            .max(2)
            .min(1)
            .build();
        int choice = field.getInput();

        // procces input
        if (choice == 2) {
            toReturn = true;
        }

        return toReturn;
    }
}
