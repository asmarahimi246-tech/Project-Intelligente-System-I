package app.games.tictactoe.players.moveStrategies;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.model.Board;
import app.games.tictactoe.model.TicTacToeMove;
import app.games.tictactoe.players.LocalPlayer;
import app.util.Logger;
/**
 * AI
 */
public class AIStrategy implements MoveSrategy {
    private Player aIToMove;
    private Player simulatedOpponent;
    private TriPredicate<Board, Integer, Integer> isOpponent = (board, row, col) -> (!board.isEmpty(row, col) && !(board.getOwner(row, col) == aIToMove));
    private TriPredicate<Board, Integer, Integer> isAi = (board, row, col) -> (!board.isEmpty(row, col) && (board.getOwner(row, col) == aIToMove));

    @FunctionalInterface
    interface TriPredicate<A, B, C> {
        boolean test(A a, B b, C c);
    }
    /**
     *
     * @param board
     * @param isAiMode
     * @param aiPlayer
     * @return
     */
    private int minimax(Board board, boolean ownMove) {
        // evaluate the board
        // returns 1 (AI) if 3 in a row
        // returns -1 (Opponent) if 3 in a row
        int score = evaluateBoard(board);

        // check if someone has won
        if (score == 1) {
            // ai won
            return 1;
        } else if (score == -1) {
            // opponent won
            return -1;
        }

        // checks if the board is full
        if (board.isFull()) {
            // draw
            return 0;
        }

        // aiMode
        if (ownMove) {
            // calculate the best ai move
            int maxscore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.isEmpty(row, col)) {
                        // copy board
                        Board copy = board.copy();

                        // place tile in the copy
                        copy.place(row, col, aIToMove);

                        // calculate score in new branch
                        int result = minimax(copy, false);

                        // check if its the best
                        maxscore = Math.max(maxscore, result);
                    }
                }
            }
            return maxscore;

        } else {
            // calculate the best opponent move
            int minscore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.isEmpty(row, col)) {
                        // copy board
                        Board copy = board.copy();

                        // place tile in the copy
                        copy.place(row, col, simulatedOpponent);

                        // calculate score in new branch
                        int result = minimax(copy, true);

                        // check if its the best (for the opponent)
                        minscore = Math.min(minscore, result);
                    }
                }
            }
            return minscore;
        }
    }
    /**
     * Calculates the best move
     *
     * @param board the board
     * @param self the ai
     * @return the move
     */
    public TicTacToeMove calculateBestMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        TicTacToeMove bestMove = null;

        // start a tree of moves at every tile
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isEmpty(row, col)) {
                    // copy board
                    Board copy = board.copy();
                    // place tile in the copy
                    copy.place(row, col, aIToMove);
                    // calculate score in new branch
                    int score = minimax(copy, false);

                    Logger.logDebug("TILE CHECKED: (" + row + "," + col + ")" + score);

                    // check if its the best
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new TicTacToeMove(row, col);
                    }
                }
            }
        }
        
        return bestMove;
    }
    /**
     * @param board
     * @return
     */
    private int evaluateBoard(Board board) {
        // checks cols
        for (int row = 0; row < 3; row++) {
            // check for ai
            if (isAi.test(board, row, 0) && isAi.test(board, row, 1) && isAi.test(board, row, 2)) {
                return 1;
            }
            // check for opponent
            if (isOpponent.test(board, row, 0) && isOpponent.test(board, row, 1) && isOpponent.test(board, row, 2)) {
                return -1;
            }
        }
        // checks rows
        for (int col = 0; col < 3; col++) {
            // check for ai
            if (isAi.test(board, 0, col) && isAi.test(board, 1, col) && isAi.test(board, 2, col)) {
                return 1;
            }

            // check for opponent
            if (isOpponent.test(board, 0, col) && isOpponent.test(board, 1, col) && isOpponent.test(board, 2, col)) {
                return -1;
            }
        }
        // checks diagonal top left to bottom right
        // check for ai
        if (isAi.test(board, 0, 0) && isAi.test(board, 1, 1) && isAi.test(board, 2, 2)) {
            return 1;
        }
        // check for opponent
        if (isOpponent.test(board, 0, 0) && isOpponent.test(board, 1, 1) && isOpponent.test(board, 2, 2)) {
            return -1;
        }
        // checks diagonal top right to bottom left
        // check for ai
        if (isAi.test(board, 2, 0) && isAi.test(board, 1, 1) && isAi.test(board, 0, 2)) {
            return 1;
        }
        // check for opponent
        if (isOpponent.test(board, 2, 0) && isOpponent.test(board, 1, 1) && isOpponent.test(board, 0, 2)) {
            return -1;
        }
        // no one can win
        return 0;
    }
    /**
     *
     * @param board the board
     * @param validateMove a predacite that true when the move is valid
     * @return
     */
    @Override
    public TicTacToeMove getMove(Game game) {
        simulatedOpponent = new LocalPlayer(this);
        simulatedOpponent.setName("S");

        // get the game
        TicTacToeGame ticTacToe = null;
        if (game instanceof TicTacToeGame) {
            ticTacToe = (TicTacToeGame) game;
        }

        // get the board
        Board board = ticTacToe.model.getBoard();

        // checks who self is
        aIToMove = ticTacToe.model.getCurrentPlayer();

        return calculateBestMove(board);
    }
}
