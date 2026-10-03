import Game.Games.TicTacToe.TicTacToeGame;
import Network.Client;
import Game.Players.Onlineplayer;
import Network.ServerListener;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // tijdelijk
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Player");
        System.out.println("2. AI");
        System.out.print("Choose player type: ");

        int choice = scanner.nextInt();

        boolean aiMode = choice == 2;
        //

        Client client = new Client();
        ServerListener listener = new ServerListener(client);
        TicTacToeGame game = new TicTacToeGame(listener, aiMode);

        listener.setGame(game);
        listener.AiMode(aiMode);
        Onlineplayer onlineplayer = new Onlineplayer(client);

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
            System.out.println(message);
        }
    }
}
