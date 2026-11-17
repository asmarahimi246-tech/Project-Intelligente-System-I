package ui.menus;

import ui.UI;
import ui.commands.MenuOption;

public class MainMenu extends Menu {

    public MainMenu(UI ui) {
        super(ui);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n== Main Menu ==\n");
        System.out.println("[1] Play a game");
        System.out.println("[x] Exit app");
        System.out.println("\nEnter choice: ");
    }

    @Override
    public MenuOption getMenuChoice() {
        String input = ui.getInput();

        return switch (input) {
            case "1" -> MenuOption.PLAY;
            case "x" -> MenuOption.EXIT;
            default -> MenuOption.INVALID;
        };
    }
}