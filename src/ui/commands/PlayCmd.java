package ui.commands;

import config.GameSetup;
import ui.DisplayManager;
import ui.menus.GameMenu;

public class PlayCmd implements Command {
    private final DisplayManager display;
    private final GameSetup setup;

    public PlayCmd(DisplayManager display) {
        this.display = display;
        this.setup = new GameSetup();
    }

    @Override
    public void execute() {
        display.setCurrentMenu(new GameMenu(display.getUI(), setup, display));
    }
}