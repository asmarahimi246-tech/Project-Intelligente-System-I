package ui.menus;

import config.GameConfig;

import config.GameSetup;
import ui.DisplayManager;
import ui.UI;
import ui.commands.ConfirmCmd;
import ui.commands.MenuOption;
import ui.commands.ResetCmd;

public class ConfirmSetupMenu extends Menu{
    private final GameConfig config;
    private final GameSetup setup;
    private final DisplayManager display;

    public ConfirmSetupMenu(UI ui,
                            GameConfig config,
                            GameSetup setup,
                            DisplayManager display) {
        super(ui);
        this.config = config;
        this.setup = setup;
        this.display = display;

        registerCommands();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n== Confirm Game Setup ==\n");
        System.out.println("Chosen setup");
        System.out.println("[Game] " + config.getType());
        System.out.println("[Mode] " + config.getMode());
        System.out.println("[Player 1] " + config.getP1Type());
        System.out.println("[Player 2] " + config.getP2Type());
        System.out.println("Is this correct? [y/n]");
    }

    @Override
    public MenuOption getMenuChoice() {
        String input = ui.getInput();

        if (input.equals("y")){
            return MenuOption.CONFIRM;
        } else if (input.equals("n")){
            return MenuOption.RESET;
        }
        return null;
    }

    private void registerCommands() {
        addCommand(MenuOption.CONFIRM,
                new ConfirmCmd(config, display));
        addCommand(MenuOption.RESET,
                new ResetCmd(setup, display));
    }
}