package ui.commands;

import config.GameSetup;
import config.options.PlayerType;
import ui.DisplayManager;
import ui.menus.PlayerMenu;

public class SetPlayer1Cmd implements Command {
    private final GameSetup setup;
    private final PlayerType player1;
    private final DisplayManager display;

    public SetPlayer1Cmd(GameSetup setup,
                        PlayerType player1,
                        DisplayManager display) {
        this.setup = setup;
        this.player1 = player1;
        this.display = display;
    }

    @Override
    public void execute() {
        setup.setP1Type(player1);
        display.setCurrentMenu(new PlayerMenu(display.getUI(),
                                2, setup, display));
    }
}