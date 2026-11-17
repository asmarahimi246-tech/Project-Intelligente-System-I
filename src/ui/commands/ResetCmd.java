package ui.commands;

import config.GameSetup;
import ui.DisplayManager;
import ui.menus.GameMenu;

public class ResetCmd implements Command {
    private DisplayManager display;
    private GameSetup setup;
    public ResetCmd(GameSetup setup,
                    DisplayManager display) {
        this.setup = setup;
        this.display = display;
    }

    @Override
    public void execute() {
        setup.reset();
        display.returnToMainMenu();
        display.setCurrentMenu(
                new GameMenu(display.getUI(), setup, display)
        );
    }
}