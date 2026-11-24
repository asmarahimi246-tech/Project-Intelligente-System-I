package app.games.framework.components;

import app.games.framework.exceptions.IlligalBoardStateException;
import app.games.framework.player.Player;
import app.games.reversi.model.Tile;

/**
 * Board
 */
public class Board {
    private Tile[][] board = null;

    /**
     * Creates a board an initializes the tiles
     */
    public Board(int width, int height) {
        this.board = new Tile[width][height];

        // create tiles
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile();
                int id = (row)*8 + col + 1;
                board[row][col].setId(id);
            }
        }
    }

    /**
     * Checks if the board is full
     *
     * @return true if its full
     */
    public boolean isFull() {
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if (board[col][row].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets a tile by its ID
     *
     * @param id the id
     * @return the tile
     */
    public Tile getById(int id) {
        Tile toReturn = null;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].getId() == id) {
                    toReturn = board[row][col];
                    break;
                }
            }
        }
        return toReturn;
    }

    /**
     * Gets a tile by its position
     *
     * @param row row
     * @param col col
     * @return the tile
     */
    public Tile getByPos(int row, int col) {
        return board[row][col];
    }

    /**
     * Creates a board from a other board
     *
     * @param other other
     */
    public Board(Board other) {
        // create tiles
        if (board != null) {
            // and copy the owner
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    board[row][col] = new Tile();

                    Player owner = other.board[row][col].getOwner();
                    board[row][col].setOwner(owner);
                }
            }
        } else {
            throw new IlligalBoardStateException("Board is null");
        }
    }

    /**
     * Sets the starting pieces in the center of the board.
     *
     * @param player1 first player
     * @param player2 second player
     */
    public void setupStartingBoard(Player player1, Player player2) {
        board[3][3].setOwner(player2);
        board[4][4].setOwner(player2);
        board[3][4].setOwner(player1);
        board[4][3].setOwner(player1);
    }

    /**
     * Copys this board
     * @return the copy
     */
    public Board copy() {
        return new Board(this);
    }
}

