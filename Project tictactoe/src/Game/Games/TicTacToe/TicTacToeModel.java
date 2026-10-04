package Game.Games.TicTacToe;

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
}
