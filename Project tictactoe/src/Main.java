import Network.Client;
import java.io.IOException;
import Framework.ServerListener;
import java.util.Scanner;
import java.util.Random;
import Framework.Game;


public class Main {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int randomnumber = 1000 + random.nextInt(9000);
        String playername = "Player" + randomnumber;

        Client client = new Client();
        Game game = new Game(client);
        ServerListener listener = new ServerListener(client, game);

        client.sendCommand("login " + playername);
        client.sendCommand("subscribe tic-tac-toe");

        String message;
        while ((message = client.getReader().readLine()) != null) {
            listener.Commandhandler(message);
            System.out.println("SERVER: " + message);
        }
    }
}
