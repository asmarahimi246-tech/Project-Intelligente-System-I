package app.network;
import app.games.tictactoe.TicTacToeController;

/**
 * > deal with incoming server messages
 * > interface so app doesn't have to keep checking for messages
 * > on a different thread please
 * 
 * > onMessage() --> pass on ServerMessage
 * > onDisconnect()
 * > onError() --> throw exception
 */
public class ServerListener {
    private Client client;
    private TicTacToeController game;
    private boolean turn = false;

    /**
     * COnstructor
     * @param client
     */
    public ServerListener(Client client) {
        this.client = client;
    }

    /**
     * TODO:
     * public void setGame(GameController game)
     * @param game
     */
    public void setGame(TicTacToeController game) {
        this.game = game;
    }

    /**
     * 
     * @param input
     */
    public void sendMoveToServer(int input) {
        client.sendCommand("move " + input);
    }

    /**
     * TODO:
     * waarom hier ?
     * @param message
     */
    public void commandhandler(String message) {
        if (message.startsWith("SVR GAME YOURTURN")) {
            turn = true;
            game.turn();
        }

        if (message.startsWith("SVR GAME MATCH")) {
            game.printBoard();
        }

        if (message.startsWith("SVR GAME MOVE")) {
            if (message.contains("MOVE:")) {
                if (!turn) {
                    game.symbol('O');
                    turn = true;
                }

                int index = message.indexOf("MOVE:") + 7;
                char caracter = message.charAt(index);
                int number = Character.getNumericValue(caracter);

                game.move(number);
            }
        }
    }
}