package ui.commands;

import config.GameConfig;
import ui.DisplayManager;

public class setMoveCmd implements Command {
    private GameConfig config;
    private DisplayManager display;

    public setMoveCmd(GameConfig config,
                      DisplayManager display) {
        this.config = config;
        this.display = display;
    }

    @Override
    public void execute() {

    }
}