package Game.Games.TicTacToe;
import Game.Players.AI;
import Network.ServerListener;

public class TicTacToeGame {
    private ServerListener listener;
    private char player1 = 'X';
    private TicTacToeModel model = new TicTacToeModel();
    private AI ai = new AI();

    public TicTacToeGame(ServerListener listener) {
        this.listener = listener;
    }

    public void Symbol(char symbol) {
        this.player1 = symbol;
    }

    public void Turn() {
        int input = view.readMove();
        input -= 1;

        if (model.placeMove(input, player1)) {
            view.printStatus();
            printBoard();

            listener.sendMoveToServer(input);
        }
    }

    public void aiMove() {

    }

    public void Move(int number) {
        char player2;

        if (player1 == 'X') {
            player2 = 'O';
        } else {
            player2 = 'X';
        }

        if (model.placeMove(number, player2)) {
            view.printStatus();
            printBoard();
        }
    }

    private TicTacToeView view = new TicTacToeView();

    public void printBoard() {
        view.printBoard(model.getBoard());
    }
}
