package Game.Players;
import Network.Client;

import java.io.IOException;
import java.util.Random;

public class Onlineplayer {
    private Client client;

    Random random = new Random();
    int randomnumber = 1000 + random.nextInt(9000);
    String playername = "Player" + randomnumber;

    public Onlineplayer(Client client) throws IOException {
        this.client = client;

        this.client.sendCommand("login " + playername);
        this.client.sendCommand("subscribe tic-tac-toe");
    }
}
