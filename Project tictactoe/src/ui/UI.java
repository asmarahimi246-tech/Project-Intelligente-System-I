package ui;

import java.util.Scanner;

public class UI {
    private Scanner scanner = new Scanner(System.in);

    public String getInput() {
        return scanner.nextLine();
    }

    public String printMessage(String message) {
        System.out.println(message);
        return getInput();
    }

    public int getMenuChoice() {
        return Integer.parseInt(getInput());
    }
}

/*
> general/generic UI layer --> Ui() = new Scanner
> DOESN'T HANDLE GAME SPECIFIC PRINTS
> get input from terminal
        ex:
        > getInput()
> print messages to terminal
        ex:
        > showMessage()
        > showError()


*/