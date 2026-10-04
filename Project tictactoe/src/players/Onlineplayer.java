/*
> player on the server
> shouldn't communicate with server directly, Client class does that
> implements Player
*/

package players;
import network.Client;
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