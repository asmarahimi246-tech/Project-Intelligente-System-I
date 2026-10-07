package app.network;
import app.games.tictactoe.TicTacToeGame;

/**
 * <pre>
 * &gt; Handles incoming server messages.
 * &gt; Defines an interface so the app can respond to messages without
 *   repeatedly checking for them.
 * &gt; Callbacks should run on a separate thread.
 *
 * &gt; onMessage()    --> receives a ServerMessage
 * &gt; onDisconnect() --> called when the connection is closed
 * &gt; onError()      --> called when an error occurs
 * </pre>
 */
public class ServerListener {
    private Client client;
    private TicTacToeGame game;

    // houd bij of deze client aan de beurt is
    private boolean turn = false;

    // houd bij of deze client als AI speelt
    private boolean aiMode = false;

    // houd bij of het symbool al is gekozen
    private boolean symbol = false;

    /**
     * TODO:
     * @param client
     */
    public ServerListener(Client client) {
        this.client = client;
    }

    /**
     * TODO:
     * @param game
     */
    public void setGame(TicTacToeGame game) {
        this.game = game;
    }

    /**
     * TODO:
     * @param aiMode
     */
    public void aiMode(boolean aiMode) {
        this.aiMode = aiMode;
    }

    /**
     * TODO:
     * @param input
     */
    public void sendMoveToServer(int input) {
        client.sendCommand("move " + input);
    }

    /**S
     * TODO:
     * @param message
     */
    public void commandhandler(String message) {
        if (message.startsWith("SVR GAME YOURTURN")) {
            turn = true;

            // de eerste speler die aan de beurt is krijgt X
            if (!symbol) {
                game.symbol('X');
                symbol = true;
            }

            // laat de AI of speler een zet doen
            if (aiMode) {
                game.aiMove();
            } else {
                game.turn();
            }
        }

        if (message.startsWith("SVR GAME MATCH")) {
            game.printBoard();
        }

        if (message.startsWith("SVR GAME MOVE")) {
            if (message.contains("MOVE:")) {
                int index = message.indexOf("MOVE:") + 7;
                char caracter = message.charAt(index);
                int number = Character.getNumericValue(caracter);

                // als de client nog geen symbool heeft is dit de tweede speler
                if (!symbol) {
                    game.symbol('O');
                    symbol = true;
                }

                // controlleert of de move van jezelf (eigen client) komt
                if (turn) {
                    game.move(number, true);
                    turn = false;
                } else {
                    // de ontvangen move komt van de andere speler
                    game.move(number, false);
                }
            }
        }

        /**
         * TODO:
         */
        if (message.startsWith("SVR GAME WIN")) {
            System.out.println("You won!");
        }

        /**
         * TODO:
         */
        if (message.startsWith("SVR GAME DRAW")) {
            System.out.println("Draw!");
        }

        /**
         * TODO:
         */
        if (message.startsWith("SVR GAME LOSS")) {
            System.out.println("You lost!");
        }
    }
}
