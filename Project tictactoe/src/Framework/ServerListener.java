package Framework;

import Network.Client;

public class ServerListener {
    private Client client;
    private Game game;
    private boolean hasHadTurn = false;

    public ServerListener(Client client, Game game) {
        this.client = client;
        this.game = game;
    }

    public void Commandhandler(String message) {
        System.out.println(message);

        if (message.startsWith("SVR GAME YOURTURN")) {
            hasHadTurn = true;
            game.Turn();
        }

        if (message.startsWith("SVR GAME MOVE")) {
            if (message.contains("MOVE:")) {
                if (!hasHadTurn) {
                    game.Symbol('O');
                    hasHadTurn = true;
                }

                int index = message.indexOf("MOVE:") + 7;
                char caracter = message.charAt(index);
                int number = Character.getNumericValue(caracter);

                game.Move(number);
            }
        }
    }

    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }
}
