package src.app.games.tictactoe.model;

import app.games.framework.player.Player;
import app.games.tictactoe.model.Board;

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
 * &gt; bool: isValidMove()
 * &gt; SwitchTurn() --&gt; change per move whose turn it is
 * &gt; bool: isGameOVer()
 * &gt; GameResult: getResult()
 * </pre>
 */
public class TicTacToeModel {
    private Player currentPlayer;
    private app.games.tictactoe.model.Board board = new app.games.tictactoe.model.Board();
    private TriPredicate<app.games.tictactoe.model.Board, Integer, Integer> isAi = (board, row, col) -> (!board.isEmpty(row, col) && (board.getOwner(row, col) == currentPlayer));

    @FunctionalInterface
    interface TriPredicate<A, B, C> {
        boolean test(A a, B b, C c);
    }

    /**
     * Gets the board
     * 
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Places a move
     */
    public void placeMove(TicTacToeMove move) {
        board.place(move.row(), move.column(), currentPlayer);
    }

    /**
     * Gets the player whos turn it is
     * 
     * @return the player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * Sets the player whos turn it is
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Checks if the current player wins
     * 
     * @return true if the player wins
     */
    public boolean checkForWin() {
        // checks cols
        for (int row = 0; row < 3; row++) {
            // check for player
            if (isAi.test(board, row, 0) && isAi.test(board, row, 1) && isAi.test(board, row, 2)) {
                return true;
            }
        }

        // checks rows
        for (int col = 0; col < 3; col++) {
            // check for player
            if (isAi.test(board, 0, col) && isAi.test(board, 1, col) && isAi.test(board, 2, col)) {
                return true;
            }
        }

        // checks diagonal top left to bottom right
        // check for player
        if (isAi.test(board, 0, 0) && isAi.test(board, 1, 1) && isAi.test(board, 2, 2)) {
            return true;
        }

        // checks diagonal top right to bottom left
        // check for player
        if (isAi.test(board, 2, 0) && isAi.test(board, 1, 1) && isAi.test(board, 0, 2)) {
            return true;
        }

        // no one can win
        return false;
    }
}