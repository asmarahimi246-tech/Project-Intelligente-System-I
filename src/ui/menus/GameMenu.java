package ui.menus;

import config.GameSetup;
import config.options.GameType;
import ui.DisplayManager;
import ui.UI;
import ui.commands.MenuOption;
import ui.commands.SetGameCmd;

public class GameMenu extends Menu {
    private final GameSetup setup;
    private final DisplayManager display;

    public GameMenu(UI ui,
                    GameSetup setup,
                    DisplayManager display) {
        super(ui);
        this.setup = setup;
        this.display = display;

        registerCommands();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n== Games ==\n");
        System.out.println("[1] Tic-tac-toe");
        System.out.println("[b] Back");
        System.out.println("[m] Return to Main Menu");
        System.out.println("[x] Exit app");
        System.out.println("\nEnter choice: ");
    }

    @Override
    public MenuOption getMenuChoice() {
        String input = ui.getInput();

        return switch (input) {
            case "1" -> MenuOption.TIC_TAC_TOE;
            case "b" -> MenuOption.BACK;
            case "m" -> MenuOption.MAIN;
            case "x" -> MenuOption.EXIT;
            default -> MenuOption.INVALID;
        };
    }

    private void registerCommands() {
        addCommand(MenuOption.TIC_TAC_TOE,
                new SetGameCmd(setup, GameType.TIC_TAC_TOE, display));
    }
}