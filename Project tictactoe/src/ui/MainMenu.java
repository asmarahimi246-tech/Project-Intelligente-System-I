package ui;

import app.GameApp;

public class MainMenu {
    private ui.UI ui = new ui.UI();
    private ui.GameMenu gameMenu = new ui.GameMenu();
    private GameApp app = new GameApp();

    public void displayMenu() {
        System.out.println("== Main Menu ==\n");
        System.out.println("1. Play game");
        System.out.println("2. Exit app\n");
        System.out.println("Enter option number: ");
    }

    public void menuChoice() {
        int input = ui.getMenuChoice();

        switch (input) {
            case 1:
                gameMenu.displayMenu(); // display game menu
            case 2:
                app.exit(); // close app
            default:
                throw new RuntimeException("Invalid input");
        }
    }
}

/*
> display main menu & get user input
> play
> exit
> options?
*/