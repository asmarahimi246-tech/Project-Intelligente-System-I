package Framework;

import Network.Client;

/**
 * ServerListener
 */
public class ServerListener {
    private Client client;
    private Game game;
    private boolean turn = false;

    /**
     * Constructor
     * Makes a serverListener with a client and game
     * 
     * @param client the client
     * @param game the game
     */
    public ServerListener(Client client, Game game) {
        this.client = client;
    }

    public void setGame(TicTacToeGame game) {
        this.game = game;
    }

    public void sendMoveToServer(int input) {
        client.sendCommand("move " + input);
    }

    public void Commandhandler(String message) {
        System.out.println(message);

        if (message.startsWith("SVR GAME YOURTURN")) {
            turn = true;
            game.Turn();
        }

        if (message.startsWith("SVR GAME MATCH")) {
            game.printBoard();
        }

        if (message.startsWith("SVR GAME MOVE")) {
            if (message.contains("MOVE:")) {
                if (!turn) {
                    game.Symbol('O');
                    turn = true;
                }

                int index = message.indexOf("MOVE:") + 7;
                char caracter = message.charAt(index);
                int number = Character.getNumericValue(caracter);

                game.Move(number);
            }
        }
    }
}
