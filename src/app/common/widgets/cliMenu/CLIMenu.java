package app.common.widgets.cliMenu;

import java.util.Scanner;

import app.common.widgets.cliInputFields.CLIIntegerField;
import app.common.widgets.cliInputFields.common.CLIField;
import app.common.widgets.cliMenu.parts.Option;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * this is the menu class.
 * it does menu stuff.
 */
public class CLIMenu {
    private String printText;
    private List<Option> optionsList;

    /**
     * It's a constructor.
     * @param printText printText.
     * @param readText readText.
     */
    public CLIMenu(String printText) {
        this.printText = printText;
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
     * This scans for input and runs the selected option. 
     * @param scanner the scanner. 
     */
    public void runOption(Scanner scanner) {
        CLIIntegerField field = new CLIIntegerField.Builder(scanner, "illegal option")
            .defaultInput(1)
            .min(-2)
            .max(optionsList.size())
            .build();
        int selectedOptionInt = field.getInput();
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
