package games.tictactoe;
import app.Game.Players.AI;
import app.Network.ServerListener;

public class TicTacToeController {
    private ServerListener listener;
    private TicTacToeModel model;
    private TicTacToeView view;
    private AI ai;
    private char player1 = 'X';

    public TicTacToeGame(ServerListener listener) {
        this.listener = listener;
        this.model = new TicTacToeModel();
        this.view = new TicTacToeView();
        this.ai = new AI();
    }

    public void Symbol(char symbol) {
        this.player1 = symbol;
    }

    public void Turn() {
        while (true) {
            int input = view.readMove();
            input -= 1;

            if (model.placeMove(input, player1)) {
                view.printTussenstand();
                printBoard();

                // als het een online game is stuurt die de zet naar de server
                if (listener != null) {
                    listener.sendMoveToServer(input);
                }
                return;
            }
            view.printInvalidMove();
        }
    }

    // laat de AI een zet doen
    public void aiMove() {
        int move = ai.numberAI(model.getBoard(), player1);

        System.out.println("AI move: " + move);

        int index = move - 1;

        if (model.placeMove(index, player1)) {
            view.printTussenstand();
            printBoard();

            // stuurt de AI move naar de server bij een online game
            if (listener != null) {
                listener.sendMoveToServer(index);
            }
        }
    }

    // verwerkt een move die lokaal is of van de tegenstander komt
    public void Move(int number, boolean ownMove) {
        char symbol;
        if (ownMove) {
            symbol = player1;
        } else {
            if (player1 == 'X') { symbol = 'O';
            } else { symbol = 'X';
            }
        }

        if (model.placeMove(number, symbol)) {
            view.printTussenstand();
            printBoard();
        }
    }

    public boolean checkWinner() {
        return model.checkWinner();
    }

    public boolean isBoardFull() {
        return model.isBoardFull();
    }

    public void printBoard() {
        view.printBoard(model.getBoard());
    }
}


/*
> connect View with Model
> get input & make Model do the work then tell View what to display

EX:
> doMove()
    --> make model check if move is valid
    --> if true, make view update with the move added
*/