package ui.commands;

import config.GameConfig;
import config.GameSetup;
import config.options.GameMode;
import ui.DisplayManager;
import ui.menus.ConfirmSetupMenu;

public class SetModeCmd implements Command {
    private final GameSetup setup;
    private final GameMode mode;
    private final DisplayManager display;

    public SetModeCmd(
            GameSetup setup,
            GameMode mode,
            DisplayManager display) {

        this.setup = setup;
        this.mode = mode;
        this.display = display;
    }

    @Override
    public void execute() {
        setup.setMode(mode);
        GameConfig config = setup.build();
        display.setCurrentMenu(
                new ConfirmSetupMenu(display.getUI(),
                        config, setup, display)
        );
    }
}