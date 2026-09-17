package Framework;

import Network.Client;
import java.util.Scanner;

public class ServerListener {
    private Client client;

    public ServerListener(Client client) {
        this.client = client;
    }

    public void Commandhandler(String message) {
        System.out.println(message);

        if (message.startsWith("SVR GAME YOURTURN")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose an number [1-9]: ");

}
