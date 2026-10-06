package app.Network;
import app.Game.Games.TicTacToe.TicTacToeGame;

public class ServerListener {
    private Client client;
    private TicTacToeGame game;

    // houd bij of deze client aan de beurt is
    private boolean turn = false;

    // houd bij of deze client als AI speelt
    private boolean aiMode = false;

    // houd bij of het symbool al is gekozen
    private boolean symbol = false;

    public ServerListener(Client client) {
        this.client = client;
    }

    public void setGame(TicTacToeGame game) {
        this.game = game;
    }

    public void AiMode(boolean aiMode) {
        this.aiMode = aiMode;
    }

    public void sendMoveToServer(int input) {
        client.sendCommand("move " + input);
    }

    public void Commandhandler(String message) {
        if (message.startsWith("SVR GAME YOURTURN")) {
            turn = true;

            // de eerste speler die aan de beurt is krijgt X
            if (!symbol) {
                game.Symbol('X');
                symbol = true;
            }

            // laat de AI of speler een zet doen
            if (aiMode) {
                game.aiMove();
            } else {
                game.Turn();
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
                    game.Symbol('O');
                    symbol = true;
                }

                // controlleert of de move van jezelf (eigen client) komt
                if (turn) {
                    game.Move(number, true);
                    turn = false;
                } else {
                    // de ontvangen move komt van de andere speler
                    game.Move(number, false);
                }
            }
        }

        if (message.startsWith("SVR GAME WIN")) {
            System.out.println("You won!");
        }

        if (message.startsWith("SVR GAME DRAW")) {
            System.out.println("Draw!");
        }

        if (message.startsWith("SVR GAME LOSS")) {
            System.out.println("You lost!");
        }
    }
}
