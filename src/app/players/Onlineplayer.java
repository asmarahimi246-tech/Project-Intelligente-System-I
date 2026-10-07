package app.players;
import app.network.Client;
import java.io.IOException;
import java.util.Random;

/**
 * <pre>
 * &gt; Represents a player on the server.
 * &gt; Does not communicate with the server directly; the {@code Client} class handles communication.
 * &gt; Implements {@code Player}.
 * </pre>
 */
public class OnlinePlayer {
    private Client client;

    Random random = new Random();
    int randomnumber = 1000 + random.nextInt(9000);
    String playername = "Player" + randomnumber;

    /**
     * TODO:
     * Constructor
     * @param client
     * @throws IOException
     */
    public OnlinePlayer(Client client) throws IOException {
        this.client = client;

        this.client.sendCommand("login " + playername);
        this.client.sendCommand("subscribe tic-tac-toe");
    }
}

/*
> represents player on the server
> shouldn't communicate with server directly, Client class does that
> implements Player
*/