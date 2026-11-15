package app.games.tictactoe.players.moveStrategies;

import java.util.Random;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.model.Board;
import app.games.tictactoe.model.TicTacToeMove;
import app.util.Config;
import app.util.Logger;

/**
 * RandomAiStrategy
 */
public class RandomAiStrategy implements MoveSrategy {

    /**
     * Main ai logic
     * 
     * @param board board
     * @param player player
     * @return the move
     */
    public int numberAI(char[] board, char player) {

        // First looks for a winning move for the AI
        for (int i = 0; i < 9; i++) {
            if (checkMove(board, player, i)) {
                return i;
            }
        }

        char opponent;

        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        // Tries to block a winning move from the opponent
        for (int i = 0; i < 9; i++) {
            if (checkMove(board, opponent, i)) {
                return i;
            }
        }

        // If there is no direct winning move or block, the AI chooses a random position
        Random random = new Random();

        while (true) {
            int randomMove = random.nextInt(9);

            // Checks if a position is free
            if (board[randomMove] != 'X' && board[randomMove] != 'O') {
                return randomMove;
            }
        }
    }

    /**
     * TODO: this
     * Evaluates the board
     * 
     * @param board board
     * @param winningMove the winning move
     * @return returns true if its a winning move
     */
    private boolean checkWinningMove(char[] board, char winningMove) {
        if (board[0] == winningMove && board[1] == winningMove && board[2] == winningMove) {
            return true;
        }
        if (board[3] == winningMove && board[4] == winningMove && board[5] == winningMove) {
            return true;
        }
        if (board[6] == winningMove && board[7] == winningMove && board[8] == winningMove) {
            return true;
        }
        if (board[0] == winningMove && board[3] == winningMove && board[6] == winningMove) {
            return true;
        }
        if (board[1] == winningMove && board[4] == winningMove && board[7] == winningMove) {
            return true;
        }
        if (board[2] == winningMove && board[5] == winningMove && board[8] == winningMove) {
            return true;
        }
        if (board[0] == winningMove && board[4] == winningMove && board[8] == winningMove) {
            return true;
        }
        if (board[2] == winningMove && board[4] == winningMove && board[6] == winningMove) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a pos can be used for a winning move
     * 
     * @param board board
     * @param player player
     * @param pos pos
     * @return true if the pos can be used
     */
    private boolean checkMove(char[] board, char player, int pos) {

        // An occupied position is not allowed
        if (board[pos] == 'X' || board[pos] == 'O') {
            return false;
        }

        char a = board[pos];
        board[pos] = player;

        // Checks if this move is a winning move
        if (checkWinningMove(board, player)) {
            board[pos] = a;
            return true;
        }
        board[pos] = a;

        return false;
    }

    /**
     * Calculates the best move
     * 
     * @param board board
     * @param aiPlayer ai
     * @return the best move
     */
    public TicTacToeMove calculateMove(char[] board, char aiPlayer) {
        // store the position of the best move found
        int move = numberAI(board, aiPlayer);

        // to move
        TicTacToeMove toReturn = new TicTacToeMove(move/3, move%3);
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

        return calculateMove(charBoard, 'X');
    }
    
}
