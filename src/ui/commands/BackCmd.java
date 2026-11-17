package ui.commands;

import ui.DisplayManager;

public class BackCmd implements Command{
    private final DisplayManager display;
    public BackCmd(DisplayManager display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.goBack();
    }
}