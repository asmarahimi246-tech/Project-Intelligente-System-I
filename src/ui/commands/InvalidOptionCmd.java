package ui.commands;

import ui.DisplayManager;

public class InvalidOptionCmd implements Command {
    private final DisplayManager display;

    public InvalidOptionCmd(DisplayManager display) {
        this.display = display;
    }

    @Override
    public void execute() {
        System.out.println("Invalid menu option!");
        display.refresh();
    }
}