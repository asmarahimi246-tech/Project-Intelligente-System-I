import Game.Games.TicTacToe.TicTacToeGame;
import Game.Players.Localplayer;
import Network.Client;
import Game.Players.Onlineplayer;
import Network.ServerListener;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Tic Tac Toe ===");
        System.out.println("1. Local game");
        System.out.println("2. Online game");
        System.out.println("Enter your choice: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            TicTacToeGame game = new TicTacToeGame(null);
            Localplayer localplayer = new Localplayer(game);
            localplayer.play();
        } else if (choice == 2) {
            Client client = new Client();
            ServerListener listener = new ServerListener(client);
            TicTacToeGame game = new TicTacToeGame(listener);

            listener.setGame(game);
            Onlineplayer onlineplayer = new Onlineplayer(client);


            String message;
            while ((message = client.getReader().readLine()) != null) {
                listener.Commandhandler(message);
                System.out.println("SERVER: " + message);

            }
        }else {
            System.out.println("Invalid choice");
        }



    }
}
