package app.games.players;

import java.io.IOException;
import java.util.Random;
import app.network.Client;

/**
 * OnlinePlayer
 */
public class Onlineplayer {
    private Client client;
    Random random = new Random();
    int randomnumber = 1000 + random.nextInt(9000);
    String playername = "Player" + randomnumber;

    /**
     * Constructor
     */
    public Onlineplayer(Client client) throws IOException {
        this.client = client;

        this.client.sendCommand("login " + playername);
        this.client.sendCommand("subscribe tic-tac-toe");
    }
}
