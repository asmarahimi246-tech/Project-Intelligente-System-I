package app.games.tictactoe.players.moveStrategies;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.model.Board;
import app.games.tictactoe.model.TicTacToeMove;
import app.util.Config;
import app.util.Logger;

/**
 * Passive ai
 */
public class PassiveAIStrategy implements MoveSrategy {

    /**
     * TODO: this
     * Evaluates the board
     * 
     * @param board board
     * @return returns a value
     */
    private int evaluateBoard(char[] board) {
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
        // checks for rows
        for (int row = 0; row < 9; row+=3) {
            if (board[row] == board[row + 1] && board[row] == board[row + 2]) {
                if (board[row] == 'X') {
                    return 1;
                }
                if (board[row] == 'O') {
                    return -1;
                }
            }
        }
        // checks for diagonals top right to bottom left
        if (board[2] == board[4] && board[2] == board[6]) {
            if (board[2] == 'X') {
                return 1;
            }
            if (board[2] == 'O') {
                return -1;
            }
        }
        // checks for diagonals top left to bottom right
        if (board[0] == board[4] && board[0] == board[8]) {
            if (board[0] == 'X') {
                return 1;
            }
            if (board[0] == 'O') {
                return -1;
            }
        }
        return 0;
    }

    /**
     * Checks if the board is full
     * 
     * @param board the board to check
     * @return true if its full
     */
    private boolean isBoardFull(char[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    /**
     * Minimax
     * 
     * @param board the board
     * @param aiPlayer ai
     * @param isAiTurn ai
     * @return a score
     */
    private int minimax(char[] board, char aiPlayer, boolean isAiTurn) {
        int score = evaluateBoard(board);

        if (score == 1) {
            if (aiPlayer == 'X') {
                return 1;
            } else {
                return -1;
            }
        }

        if (score == -1) {
            if (aiPlayer == 'O') {
                return 1;
            } else {
                return -1;
            }
        }

        if (isBoardFull(board)) {
            return 0;
        }

        char simulatedOpponent;

        if (aiPlayer == 'X') {
            simulatedOpponent = 'O';
        } else {
            simulatedOpponent = 'X';
        }

        if (isAiTurn) {
            // ai turn find the move with the highest possible score
            int maxscore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    // saving original value so move can be undone
                    char original = board[i];
                    // simulate ai placing symbol on posistion
                    board[i] = aiPlayer;
                    // calculate the score of move by simulating opponents turn
                    int result = minimax(board, aiPlayer, false);
                    // undo simulated move
                    board[i] = original;
                    // keep the highest score found
                    maxscore = Math.max(maxscore, result);
                }
            }
            return maxscore;

        } else {
            // opponent turn find a move with the lowest possible score for the ai
            int minscore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char original = board[i];
                    board[i] = simulatedOpponent;
                    int result = minimax(board, aiPlayer, true);
                    board[i] = original;
                    // keep the lowest score found
                    minscore = Math.min(minscore, result);
                }
            }
            // return lowest score the opponent can force for the ai
            return minscore;
        }
    }

    /**
     * Calculates the best move
     * 
     * @param board board
     * @param aiPlayer ai
     * @return the best move
     */
    public TicTacToeMove calculateBestMove(char[] board, char aiPlayer) {
        // lowest possible score
        int bestScore = Integer.MIN_VALUE;
        // store the position of the best move found
        int bestMove = 0;

        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                char original = board[i];
                board[i] = aiPlayer;
                // use minimax to calculate the score of this move
                int score = minimax(board, aiPlayer, false);
                board[i] = original;
                // update the best move and move if this move is better
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            } 
        }

        // to move
        TicTacToeMove toReturn = new TicTacToeMove(bestMove/3, bestMove%3);
        return toReturn;
    }

    @Override
    public TicTacToeMove getMove(Game game) {
        Logger.logDebug("Passive ai is doing a move");

        // get the game
        TicTacToeGame ticTacToe = null;
        if (game instanceof TicTacToeGame) {
            ticTacToe = (TicTacToeGame) game;
        }

        // get the board
        Board board = ticTacToe.model.getBoard();

        // checks who self is
        Player aIPlayer = ticTacToe.model.getCurrentPlayer();

        // make board
        char[] charBoard = new char[9];
        for (int i = 1; i <= 9; i++) {
            if(board.getById(i).getOwner() == aIPlayer) {
                charBoard[i - 1] = 'X';
            } else if (board.getById(i).isEmpty()) {
                charBoard[i - 1] = '_';
            } else {
                charBoard[i - 1] = 'O';
            }

            if (Config.DEBUG) {
                System.out.print(charBoard[i - 1]);
                if (i%3 == 0) {
                    System.out.print("\n");
                }
            }
        }

        return calculateBestMove(charBoard, 'X');
    }
    
}
