package app.games.tictactoe.model;

import java.util.ArrayList;

import app.games.framework.player.Player;

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
    private Board board = new Board();

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

    public boolean validateMove(TicTacToeMove toReturn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateMove'");
    }
}
