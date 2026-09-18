package Framework;

import Network.Client;

public class ServerListener {
    private Client client;
    private Game game;
    private boolean turn = false;

    public ServerListener(Client client, Game game) {
        this.client = client;
        this.game = game;
    }

    public void Commandhandler(String message) {
        System.out.println(message);

        if (message.startsWith("SVR GAME YOURTURN")) {
            turn = true;
            game.Turn();
        }

        if (message.startsWith("SVR GAME MOVE")) {
            if (message.contains("MOVE:")) {
                if (!turn) {
                    game.Symbol('O');
                    hadturn = true;
                }

                int index = message.indexOf("MOVE:") + 7;
                char caracter = message.charAt(index);
                int number = Character.getNumericValue(caracter);

                game.Move(number);
            }
        }
    }
}

