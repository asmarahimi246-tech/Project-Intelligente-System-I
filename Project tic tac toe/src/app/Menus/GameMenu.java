package app.Menus; import java.util.Scanner; public class GameMenu {
    private Scanner scanner;

    public GameMenu() {
        scanner = new Scanner(System.in);
    }

    public boolean mainMenu() {
        System.out.println("=== Tic Tac Toe ===");
        System.out.println("1. Local game");
        System.out.println("2. Online game");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        return choice == 2;
    }

    // speler kan kiezen tussen Player en AI
    public boolean menu(boolean online) {
        System.out.println();

        if (online) {
            System.out.println("===Online app.Game===");
            System.out.println("1. Player");
            System.out.println("2. AI");

        } else {
            System.out.println("===Local app.Game===");
            System.out.println("1. Player");
            System.out.println("2. AI");
        }

        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        return choice == 2;
    }
}
