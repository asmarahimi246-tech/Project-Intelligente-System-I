package app.games.tictactoe;

/**
 * <pre>
 * &gt; tictactoe rules &amp; game state
 *     &gt; board
 *     &gt; whose turn it is (X/O)
 *     &gt; if move is legal
 *     &gt; how moves change the board
 *     &gt; when win/loss/draw happens
 *     &gt; who won (X/O)
 *
 * &gt; must work separate from
 *     &gt; how board is displayed
 *     &gt; where input came from (terminal/gui/server)
 *     &gt; what type of player made the move (ai/human)
 *
 * EX:
 * &gt; doMove() --&gt; send move to Controller so it can tell View to update with the move made
 * &gt; bool: isValidMove()
 * &gt; SwitchTurn() --&gt; change per move whose turn it is
 * &gt; bool: isGameOVer()
 * &gt; GameResult: getResult()
 * </pre>
 */
public class TicTacToeModel {
    private char[] board = {'1','2','3','4','5','6','7','8','9'};

    /**
     * TODO:
     * @param number
     * @param symbol
     * @return
     */
    public boolean placeMove(int number, char symbol) {
        if (board[number] != 'X' && board[number] != 'O') {
            board[number] = symbol;
            return true;
        }
        return false;
    }

    /**
     * TODO:
     * @return
     */
    public char[] getBoard() {
        return board;
    }

    /**
     * TODO:
     * @return
     */
    public boolean isBoardFull() {
        for (char pos: board) {
            if (pos != 'X' && pos != 'O') {
                return false;
            }
        }
        return true;
    }

    /**
     * TODO:
     * @return
     */
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
