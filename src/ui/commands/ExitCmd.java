package ui.commands;

import ui.DisplayManager;

public class ExitCmd implements Command {

    private final DisplayManager display;

    public ExitCmd(DisplayManager display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.stop();
    }
}