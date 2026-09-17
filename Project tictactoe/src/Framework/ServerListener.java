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


}
