package src.app.games.tictactoe.model;

import app.games.framework.player.Player;
import app.games.tictactoe.model.Tile;

/**
 * Board
 */
public class Board {
    private final Tile[][] board = new Tile[3][3];

    @Override
    public String toString() {
        String toReturn = "";

        // loop trough all tiles
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                // get the owner of a tile
                Player owner = board[row][col].owner;

                // add spacer
                if (col != 0) {
                    toReturn += "|";
                }

                // check if its null
                if (owner == null) {
                    toReturn += "_";
                } else {
                    // Place first char of name if not null
                    String playerName = board[row][col].owner.getName().toUpperCase();
                    toReturn += playerName.charAt(0);
                }
            }
            toReturn += "\n";
        }
        
        return toReturn;
    }

    /**
     * Creates a board an initializes the tiles
     */
    public Board() {
        // create tiles
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile();
            }
        }
    }

    /**
     * Checks if the tile is empty
     * 
     * @param row row
     * @param col collumn
     * @return true if empty
     */
    public boolean isEmpty(int row, int col) {
        return board[row][col].isEmpty();
    }

    /**
     * Checks if the board is full
     * 
     * @return true if its full
     */
    public boolean isFull() {
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if (board[col][row].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Places a tile
     * 
     * @param row row
     * @param col collumn
     * @param player the player that places the tile
     */
    public void place(int row, int col, Player player) {
        board[row][col].setOwner(player);
    }

    /**
     * Gets the owner
     * 
     * @param row row
     * @param col collumn
     * @return the owner
     */
    public Player getOwner(int row, int col) {
        return board[row][col].getOwner();
    }

    /**
     * Creates a board from a other board
     * 
     * @param other other
     */
    public Board(Board other) {
        // create tiles
        // and copy the owner
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile();

                Player owner = other.board[row][col].getOwner();
                board[row][col].setOwner(owner);
            }
        }
    }

    /**
     * Copys this board
     * @return the copy
     */
    public Board copy() {
        return new Board(this);
    }
}