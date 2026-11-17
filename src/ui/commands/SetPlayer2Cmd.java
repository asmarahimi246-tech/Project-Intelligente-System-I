package ui.commands;

import config.GameSetup;
import config.options.PlayerType;
import ui.DisplayManager;
import ui.menus.ModeMenu;

public class SetPlayer2Cmd implements Command {
    private final GameSetup setup;
    private final PlayerType player2;
    private final DisplayManager display;

    public SetPlayer2Cmd(GameSetup setup,
                         PlayerType player2,
                         DisplayManager display) {
        this.setup = setup;
        this.player2 = player2;
        this.display = display;
    }

    @Override
    public void execute() {
        setup.setP2Type(player2);
        display.setCurrentMenu(new ModeMenu(display.getUI(), setup, display));

        // show confirm setup menu?
        //      -> show settings, ask confirm
        //          y = config.build(), go to gameView (or smth, to be implemented)
        //          n = go back to modeMenu


    }
}