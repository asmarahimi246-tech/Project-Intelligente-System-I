package ui.menus;

import ui.UI;
import ui.commands.Command;
import ui.commands.MenuOption;

import java.util.EnumMap;
import java.util.Map;

public abstract class Menu {

    protected final UI ui;

    private final Map<MenuOption, Command> commands =
            new EnumMap<>(MenuOption.class);

    public Menu(UI ui) {
        this.ui = ui;
    }

    protected void addCommand(
            MenuOption option,
            Command command) {

        commands.put(option, command);
    }

    public void executeCommand(MenuOption option) {

        Command command = commands.get(option);

        if (command == null) {
            System.out.println("No command found for option " + option);
            return;
        }

        command.execute();
    }

    public abstract void displayMenu();

    public abstract MenuOption getMenuChoice();
}