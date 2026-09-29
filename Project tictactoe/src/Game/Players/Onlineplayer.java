package Game.Players;
import Network.Client;

import java.io.IOException;
import java.util.Random;

public class Onlineplayer {
    Client client = new Client();

    Random random = new Random();
    int randomnumber = 1000 + random.nextInt(9000);
    String playername = "Player" + randomnumber;

    public Onlineplayer() throws IOException {
        client.sendCommand("login " + playername);
        client.sendCommand("subscribe tic-tac-toe");
    }
}
