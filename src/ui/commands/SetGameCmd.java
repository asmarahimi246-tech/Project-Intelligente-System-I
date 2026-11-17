package ui.commands;

import config.GameSetup;
import config.options.GameType;
import ui.DisplayManager;
import ui.menus.PlayerMenu;

public class SetGameCmd implements Command {
    private final GameSetup setup;
    private final GameType type;
    private final DisplayManager display;


    public SetGameCmd(GameSetup setup,
                      GameType type,
                      DisplayManager display) {
        this.setup = setup;
        this.type = type;
        this.display = display;
    }


    @Override
    public void execute() {
        setup.setGameType(type);
        display.setCurrentMenu(new PlayerMenu(display.getUI(),
                                1, setup, display));
    }
}