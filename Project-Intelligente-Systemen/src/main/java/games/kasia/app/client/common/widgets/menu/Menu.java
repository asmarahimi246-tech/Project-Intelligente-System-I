package games.kasia.app.client.common.widgets.menu;

import java.util.Scanner;

import games.kasia.app.client.common.widgets.menu.parts.Option;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * this is the menu class.
 * it does menu stuff.
 */
public class Menu {
    private String printText;
    private String readText;
    private List<Option> optionsList;

    /**
     * It's a constructor.
     * @param printText printText.
     * @param readText readText.
     */
    public Menu(String printText, String readText) {
        this.printText = printText;
        this.readText = readText;;
        this.optionsList = new ArrayList<>();
    }
    
    public void addOption(Option option){
        optionsList.add(option);
    }

    /**
     * This prints the option descriptions.
     * Uses Tread.sleep for fancy printing.
     */
    public void printOptionsList() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted");
        }
        System.out.println(printText);
        int optionNumber = 0;
        for (Option option : this.optionsList) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted");
            }
            System.out.println("(" + optionNumber + ") " + option.getDescription());
            optionNumber += 1;
        }
    }

    /**
     * Does input for int and some error handling.
     * @param scanner the scanner.
     * @param errorMessage the errorMessage to print when an error occurs.
     * @param defaultInt the defaultInt to fall back on.
     * @param min the min value that is accepted.
     * @param max the max value that is accepted.
     * @return toReturn the value to return.
     */
    public int inputInt(Scanner scanner, String errorMessage, int defaultInt, int min, int max){
        int toReturn = defaultInt;
        try {
            toReturn = scanner.nextInt();
            if(toReturn > max || toReturn < min){
                System.err.println("input out of range");
                System.err.println("using default: " + defaultInt);
                toReturn = defaultInt;
                System.err.println("press enter to exit error");
                scanner.nextLine();
            }
        } catch (InputMismatchException e) {
            System.err.println(errorMessage);
            System.err.println("using default: " + defaultInt);
            System.err.println("press enter to exit error");
            scanner.nextLine();
        }
        scanner.nextLine();
        return toReturn;
    }

    /**
     * This scans for input and runs the selected option. 
     * @param scanner the scanner. 
     */
    public void runOption(Scanner scanner) {
        System.out.println(readText);
        int selectedOptionInt = this.inputInt(scanner, "illegal option", -1, -2, optionsList.size());
        //this.clearScreen();
        if (selectedOptionInt < 0 || selectedOptionInt > this.optionsList.size() - 1){
            System.out.println("");
        } else {
            Option selectedOption = this.optionsList.get(selectedOptionInt);
            selectedOption.run(scanner);
        }
    }

    /**
     * This function clears the screen for the next frame. 
     */
    public void clearScreen() {
        final int width = 108;  // Set your terminal width
        final int height = 72; // Set your terminal height
    
        // Move cursor to top-left
        System.out.print("\033[H");
    
        // Fill the screen with spaces
        String blankLine = " ".repeat(width);
        for (int i = 0; i < height; i++) {
            System.out.println(blankLine);
        }
    
        System.out.flush();

        // Move cursor to top-left
        System.out.print("\033[H");
        System.out.println();
        System.out.println();
    }
}
