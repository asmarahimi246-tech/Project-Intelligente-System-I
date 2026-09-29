package Game.Players;
import java.util.Random;

public class Onlineplayer {
    Random random = new Random();
    int randomnumber = 1000 + random.nextInt(9000);
    String playername = "Player" + randomnumber;
    client.sendCommand("login " + playername);
    client.sendCommand("subscribe tic-tac-toe");
}
