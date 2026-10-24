package app.games.tictactoe.players.moveStrategies;
import java.util.ArrayList;
import java.util.function.Predicate;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.model.Board;
import app.games.tictactoe.model.TicTacToeMove;
/**
 * AI
 */
public class AIStrategy implements MoveSrategy {
    private Player aIToMove;
    private Player simulatedOpponent;

    /**
     *
     * @param board
     * @param isAiModew
     * @param aiPlayer
     * @return
     */
    private int minimax(Board board, boolean ownMove) {
        // evaluate the board
        // returns 1 (X) if 3 in a row
        // returns -1 (O) if 3 in a row
        //int score = evaluateBoard(board);

        // evaluate the board
        // returns 1 (AI) if 3 in a row
        // returns -1 (Opponent) if 3 in a row
        int score = evaluateBoard(board);

        // base case
        // ai loses or wins
        //if (score == 1) {
        //    // win for X
        //    if (aiPlayer == 'X') {
        //        return 1;
        //    // loss for X
        //    } else {
        //        return -1;
        //    }
        //}

        //if (score == -1) {
        //    // win for O
        //    if (aiPlayer == 'O') {
        //        return 1;
        //    // loss for )
        //    } else {
        //        return -1;
        //    }
        //}

        // check if someone has won
        if (score != 0) {
            return score;
        }

        // checks if the board is full
        if (board.isFull()) {
            // draw
            return 0;
        }

        // makes opponent
        //char simulatedOpponent;

        // opponent char is opposite from self
        //if (aiPlayer == 'X') {
        //    simulatedOpponent = 'O';
        //} else {
        //    simulatedOpponent = 'X';
        //}

        // copy board
        Board copy = board.copy();

        // aiMode
        if (ownMove) {
            int maxscore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.isEmpty(row, col)) {
                        // place tile in the copy
                        copy.place(row, col, aIToMove);

                        int result = minimax(board, false);
                        //board[i] = original;
                        maxscore = Math.max(maxscore, result);
                    }
                }
            }
            return maxscore;
        } else {
            int minscore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.isEmpty(row, col)) {
                        // place tile in the copy
                        copy.place(row, col, simulatedOpponent);

                        //get original
                        //char original = board[i];
                        //board[i] = simulatedOpponent;

                        int result = minimax(board, true);
                        //board[i] = original;
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

        // copy board
        Board copy = board.copy();

        // start a tree of moves at every tile
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isEmpty(row, col)) {

                    // place tile in the copy
                    copy.place(row, col, aIToMove);

                    // calculate score
                    int score = minimax(copy, false);

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

        // checks rows
        for (int row = 0; row < 3; row++) {
            if (board.getOwner(row, 0) == board.getOwner(row, 1) && board.getOwner(row, 0) == board.getOwner(row, 2)) {
                if (board.getOwner(row, 0) == aIToMove) {
                    return 1;
                }
                if (board.getOwner(row, 0) == simulatedOpponent) {
                    return -1;
                }
            }
        }

        // checks columns
        for (int col = 0; col < 3; col++) {
            if (board.getOwner(0, col) == board.getOwner(1, col) && board.getOwner(0, col) == board.getOwner(2, col)) {
                if (board.getOwner(0, col) == aIToMove) {
                    return 1;
                }
                if (board.getOwner(0, col) == simulatedOpponent) {
                    return -1;
                }
            }
        }


        // checks diagonal top left to bottom right
        if (board.getOwner(0, 0) == board.getOwner(1, 1) && board.getOwner(0, 0) == board.getOwner(2, 2)) {
            if (board.getOwner(0, 0) == aIToMove) {
                return 1;
            }
            if (board.getOwner(0, 0) == simulatedOpponent) {
                return -1;
            }
        }

        // checks diagonal top right to bottom left
        if (board.getOwner(2, 0) == board.getOwner(1, 1) && board.getOwner(0, 2) == board.getOwner(2, 2)) {
            if (board.getOwner(0, 0) == aIToMove) {
                return 1;
            }
            if (board.getOwner(0, 0) == simulatedOpponent) {
                return -1;
            }
        }
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
        // get the game
        TicTacToeGame ticTacToe = null;
        if (game instanceof TicTacToeGame) {
            ticTacToe = (TicTacToeGame) game;
        }

        // get the board
        Board board = ticTacToe.model.getBoard();

        // checks who self is
        aIToMove = ticTacToe.model.getCurrentPlayer();

        //char[] charBoard = new char[9];
        //int playedTurns = 0;

        // count round
        //for (int i = 0; i < 9; i++) {
        //    charBoard[i] = board.get(i).charAt(0);
        //    if (charBoard[i] == 'X' || charBoard[i] == 'O') {
        //        playedTurns++;
        //    }
        //}

        // first player = O
        //char aiPlayer;
        //if (playedTurns % 2 == 0) {
        //    aiPlayer = 'X';
        //} else {
        //    aiPlayer = 'O';
        //}

        // pass the char
        return calculateBestMove(board);
    }
}
