package app.games.tictactoe.players.moveStrategies;
import java.util.ArrayList;
import java.util.function.Predicate;
/**
 * AI
 */
public class AIStrategy implements MoveSrategy {
    /**
     *
     * @param board
     * @param isMax
     * @param AiPlayer
     * @return
     */
    private int minimax(char[] board, boolean isMax, char AiPlayer) {
        int score = evaluateBoard(board);

        if (score == 1) {
            if (AiPlayer == 'X') {
                return 1;
            } else {
                return -1;
            }
        }

        if (score == -1) {
            if (AiPlayer == 'O') {
                return 1;
            } else {
                return -1;
            }
        }

        if (isBoardFull(board)) {
            return 0;
        }

        char simulatedOpponent;

        if (AiPlayer == 'X') {
            simulatedOpponent = 'O';
        } else {
            simulatedOpponent = 'X';
        }

        if (isMax) {
            int maxscore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char original = board[i];
                    board[i] = AiPlayer;
                    int result = minimax(board, false, AiPlayer);
                    board[i] = original;
                    maxscore = Math.max(maxscore, result);
                }
            }
            return maxscore;
        } else {
            int minscore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char original = board[i];
                    board[i] = simulatedOpponent;
                    int result = minimax(board, true, AiPlayer);
                    board[i] = original;
                    minscore = Math.min(minscore, result);
                }
            }
            return minscore;
        }
    }
    /**
     *
     * @param board
     * @param AiPlayer
     * @return
     */
    public Move calculateBestMove(char[] board, char AiPlayer) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = 0;

        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                char original = board[i];
                board[i] = AiPlayer;
                int score = minimax(board, false, AiPlayer);
                board[i] = original;
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        int calcRow = bestMove / 3;
        int calcCol = bestMove % 3;
        return new Move(calcRow, calcCol);
    }
    /**
     *
     * @param board
     * @return
     */
    private int evaluateBoard(char[] board) {
        // checks rows
        for (int row = 0; row <  9; row+=3) {
            if (board[row] == board[row + 1] && board[row] == board[row + 2]) {
                if (board[row] == 'X') {
                    return 1;
                }
                if (board[row] == 'O') {
                    return -1;
                }
            }
        }
        // checks columns
        for (int col = 0; col < 3; col++) {
            if (board[col] == board[col + 3] && board[col] == board[col + 6]) {
                if (board[col] == 'X') {
                    return 1;
                }
                if (board[col] == 'O') {
                    return -1;
                }
            }
        }
        // checks diagonal top left to bottom right
        if (board[0] == board[4] && board[0] == board[8]) {
            if (board[0] == 'X') {
                return 1;
            }
            if (board[0] == 'O') {
                return -1;
            }
        }
        // checks diagonal top right to bottom left
        if (board[2] == board[4] && board[2] == board[6]) {
            if (board[2] == 'X') {
                return 1;
            }
            if (board[2] == 'O') {
                return -1;
            }
        }
        return 0;
    }
    /**
     *
     * @param board
     * @return
     */
    private boolean isBoardFull(char[] board) {
        for (char vak : board) {
            if (vak != 'X' && vak != 'O') {
                return false;
            }
        }
        return true;
    }
    /**
     *
     * @param board the board
     * @param validateMove a predacite that true when the move is valid
     * @return
     */
    @Override
    public Move getMove(ArrayList<String> board, Predicate<Move> validateMove) {
        char[] charBoard = new char[9];
        int playedTurns = 0;

        for (int i = 0; i < 9; i++) {
            charBoard[i] = board.get(i).charAt(0);
            if (charBoard[i] == 'X' || charBoard[i] == 'O') {
                playedTurns++;
            }
        }

        char AiPlayer;
        if (playedTurns % 2 == 0) {
            AiPlayer = 'X';
        } else {
            AiPlayer = 'O';
        }
        return calculateBestMove(charBoard, AiPlayer);
    }
}
