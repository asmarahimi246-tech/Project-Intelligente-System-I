package ui.commands;

import ui.DisplayManager;

public class ToMainMenuCmd implements Command {
    private final DisplayManager display;

    public ToMainMenuCmd(DisplayManager display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.returnToMainMenu();
    }
}