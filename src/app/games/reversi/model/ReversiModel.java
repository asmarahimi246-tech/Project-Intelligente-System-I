package app.games.reversi.model;

import app.games.framework.exceptions.InvalidMoveException;
import app.games.framework.player.Player;
import app.util.Logger;

/**
 *
 */
public class ReversiModel {
    private Player currentPlayer;
    private Board board = new Board();
    private app.games.reversi.model.ReversiModel.TriPredicate<Board, Integer, Integer> isCurrentPlayer = (board, row, col) -> (!board.getByPos(row, col).isEmpty()) && (board.getByPos(row, col).getOwner() == currentPlayer);

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
     *
     * @param move the move to place
     * @throws InvalidMoveException when the move is invalid
     */
    public void placeMove(ReversiMove move) throws InvalidMoveException {
        if (board.getByPos(move.row(), move.column()).isEmpty()) {
            board.getByPos(move.row(), move.column()).setOwner(currentPlayer);
        } else {
            throw new InvalidMoveException("invalid move (tile not empty): " + move.row() + "," + move.column());
        }
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
     *
     * @param currentPlayer the current player
     */
    public void setCurrentPlayer(Player currentPlayer) {
        Logger.logDebug("current player = " + currentPlayer.getName());
        this.currentPlayer = currentPlayer;
    }

    /**
     * Checks if the current player wins
     *
     * @return true if the player wins
     */
//    public boolean checkForWin() {
//    }

    /**
     * Sets the board
     *
     * @param toSet the board to set it to
     */
    public void setBoard(Board toSet) {
        board = toSet;
    }
}
