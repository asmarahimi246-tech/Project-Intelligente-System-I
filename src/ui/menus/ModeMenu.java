package ui.menus;

import config.GameSetup;
import config.options.GameMode;
import ui.DisplayManager;
import ui.UI;
import ui.commands.MenuOption;
import ui.commands.SetModeCmd;

public class ModeMenu extends Menu {
    private final GameSetup setup;
    private final DisplayManager display;

    public ModeMenu(
            UI ui,
            GameSetup setup,
            DisplayManager display) {

        super(ui);

        this.setup = setup;
        this.display = display;

        registerCommands();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n== GameSetup Mode ==\n");
        System.out.println("[1] Local");
        System.out.println("[2] Remote");
        System.out.println("[b] Back");
        System.out.println("[m] Return to Main Menu");
        System.out.println("[x] Exit");
        System.out.println("\nEnter choice ");
    }

    @Override
    public MenuOption getMenuChoice() {
        String input = ui.getInput();

        return switch (input) {
            case "1" -> MenuOption.LOCAL;
            case "2" -> MenuOption.REMOTE;
            case "b" -> MenuOption.BACK;
            case "m" -> MenuOption.MAIN;
            case "x" -> MenuOption.EXIT;
            default -> MenuOption.INVALID;
        };
    }

    private void registerCommands() {
        addCommand(MenuOption.LOCAL,
                new SetModeCmd(setup, GameMode.LOCAL, display));
        addCommand(MenuOption.REMOTE,
                new SetModeCmd(setup, GameMode.ONLINE, display));
    }
}