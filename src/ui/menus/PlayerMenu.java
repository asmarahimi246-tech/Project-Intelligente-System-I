package ui.menus;

import config.GameSetup;
import config.options.PlayerType;
import ui.DisplayManager;
import ui.UI;
import ui.commands.MenuOption;
import ui.commands.SetPlayer1Cmd;
import ui.commands.SetPlayer2Cmd;

public class PlayerMenu extends Menu {
    private final int playerNumber;
    private final GameSetup setup;
    private final DisplayManager display;

    public PlayerMenu(
            UI ui,
            int playerNumber,
            GameSetup setup,
            DisplayManager display) {

        super(ui);

        this.playerNumber = playerNumber;
        this.setup = setup;
        this.display = display;

        registerCommands();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n== Select Player " + playerNumber + " ==\n");
        System.out.println("[1] Human");
        System.out.println("[2] Bot");
        System.out.println("[b] Back");
        System.out.println("[m] Return to Main Menu");
        System.out.println("[x] Exit");
        System.out.println("\nEnter choice: ");
    }

    @Override
    public MenuOption getMenuChoice() {
        String input = ui.getInput();

        return switch (input) {
            case "1" -> MenuOption.HUMAN;
            case "2" -> MenuOption.BOT;
            case "b" -> MenuOption.BACK;
            case "m" -> MenuOption.MAIN;
            case "x" -> MenuOption.EXIT;
            default -> MenuOption.INVALID;
        };
    }

    private void registerCommands() {
        if (playerNumber == 1) {
            addCommand(MenuOption.HUMAN,
                    new SetPlayer1Cmd(
                            setup,
                            PlayerType.HUMAN,
                            display));

            addCommand(MenuOption.BOT,
                    new SetPlayer1Cmd(
                            setup,
                            PlayerType.BOT,
                            display));

        } else {
            addCommand(MenuOption.HUMAN,
                    new SetPlayer2Cmd(
                            setup,
                            PlayerType.HUMAN,
                            display));

            addCommand(MenuOption.BOT,
                    new SetPlayer2Cmd(
                            setup,
                            PlayerType.BOT,
                            display));
        }
    }
}

// how to use
// new PlayerMenu(ui, 1, setup, display);
// new PlayerMenu(ui, 2, setup, display);