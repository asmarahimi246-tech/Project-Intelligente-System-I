package app.Game.Games.TicTacToe;

public class TicTacToeModel {
    private char[] board = {'1','2','3','4','5','6','7','8','9'};

    public boolean placeMove(int number, char symbol) {
        if (board[number] != 'X' && board[number] != 'O') {
            board[number] = symbol;
            return true;
        }
        return false;
    }

    public char[] getBoard() {
        return board;
    }

    public boolean isBoardFull() {
        for (char pos: board) {
            if (pos != 'X' && pos != 'O') {
                return false;
            }
        }
        return true;
    }

    public boolean checkWinner() {
        if (board[0] == board[1] && board[1] == board[2]) {
            return true;
        }
        if (board[3] == board[4] && board[4] == board[5]) {
            return true;
        }
        if (board[6] == board[7] && board[7] == board[8]) {
            return true;
        }
        if (board[0] == board[3] && board[3] == board[6]) {
            return true;
        }
        if (board[1] == board[4] && board[4] == board[7]) {
            return true;
        }
        if (board[2] == board[5] && board[5] == board[8]) {
            return true;
        }
        if (board[0] == board[4] && board[4] == board[8]) {
            return true;
        }
        if (board[2] == board[4] && board[4] == board[6]) {
            return true;
        }
        return false;
    }
}
