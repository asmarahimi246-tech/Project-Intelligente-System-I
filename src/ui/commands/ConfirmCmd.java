package ui.commands;

import config.GameConfig;
import ui.DisplayManager;
import ui.menus.GamePage;

public class ConfirmCmd implements Command {
    private final DisplayManager display;
    private final GameConfig config;

    public ConfirmCmd(GameConfig config,
                      DisplayManager display) {
        this.config = config;
        this.display = display;
    }

    @Override
    public void execute() {
        display.setCurrentMenu(
                new GamePage(display.getUI(), config, display));
    }
}