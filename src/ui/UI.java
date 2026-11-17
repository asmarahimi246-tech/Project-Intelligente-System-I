package ui;

import java.util.Scanner;

public class UI {
    private static final Scanner scanner = new Scanner(System.in);

    // get user input from terminal
    public String getInput() {
        return scanner.nextLine();
    }

    // print given message
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}


//public class CLI implements UI {
//
//    private final Scanner scanner = new Scanner(System.in);
//
//    @Override
//    public MenuOption displayMenu(Menu menu) {
//
//        System.out.println("\n== " + menu.getTitle() + " ==\n");
//
//        var options = menu.getOptions();
//
//        for (int i = 0; i < options.size(); i++) {
//            System.out.printf("[%d] %s%n",
//                    i + 1,
//                    options.get(i).label());
//        }
//
//        System.out.print("\nEnter choice: ");
//
//        try {
//            int choice = Integer.parseInt(scanner.nextLine()) - 1;
//
//            if (choice >= 0 && choice < options.size()) {
//                return options.get(choice).option();
//            }
//        } catch (NumberFormatException ignored) {}
//
//        return MenuOption.INVALID;
//    }
//
//    @Override
//    public void printMessage(String message) {
//        System.out.println(message);
//    }
//
//    @Override
//    public void close() {
//        scanner.close();
//    }
//}