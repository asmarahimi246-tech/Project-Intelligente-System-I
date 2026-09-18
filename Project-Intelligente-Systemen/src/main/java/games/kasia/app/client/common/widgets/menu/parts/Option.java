package games.kasia.app.client.common.widgets.menu.parts;

import java.util.Scanner;

/**
 * This is the option class, it has a description and a function.
 */
public class Option {
    private String description;
    private ToExecute function;

    public Option(String description, ToExecute function) {
        this.function = function;
        this.description = description;
    }

    /**
     * Executes the function.
     * @param scanner the scanner.
     */
    public void run(Scanner scanner) {
        function.execute();
    }

    public String getDescription() {
        return description;
    }
}
