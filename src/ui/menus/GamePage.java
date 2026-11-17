package ui.menus;

import config.GameConfig;
import config.GameLauncher;
import ui.DisplayManager;
import ui.UI;
import ui.commands.MenuOption;
import ui.commands.setMoveCmd;

public class GamePage extends Menu {
    private final GameConfig config;
    private final DisplayManager display;
    private final GameLauncher launcher;

    public GamePage(UI ui,
                    GameConfig config,
                    DisplayManager display) {
        super(ui);
        this.config = config;
        this.display = display;
        this.launcher = new GameLauncher(config);

        registerCommands();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n== " + config.getType() + " ==\n");
        System.out.println("[b] Back");
        System.out.println("[m] Return to Main Menu");
        System.out.println("[x] Exit app");
    }

    @Override
    public MenuOption getMenuChoice() {
        String input = ui.getInput();

        return switch (input) {
            case "b" -> MenuOption.BACK;
            case "m" -> MenuOption.MAIN;
            case "x" -> MenuOption.EXIT;
            case "move" -> MenuOption.MOVE;
            default -> MenuOption.INVALID;
        };
    }

    private void registerCommands() {
        addCommand(MenuOption.MOVE,
                new setMoveCmd(config, display));
    }
}