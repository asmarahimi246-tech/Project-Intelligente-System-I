package app.games.framework.components;

import app.games.framework.exceptions.IlligalBoardStateException;
import app.games.framework.player.Player;
import app.util.Logger;

/**
 * Board
 */
public class Board {
    private Tile[][] board = null;
    public int width;
    public int height;

    /**
     * Creates a board an initializes the tiles
     * 
     * @param width width
     * @param height height
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Tile[width][height];

        // create tiles
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                board[row][col] = new Tile();
                int id = (row)*width + col + 1;
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
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
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
        return (Tile) board[row][col];
    }

    /**
     * Creates a board from a other board
     *
     * @param other other
     * 
     * @throws IlligalBoardStateException when the board to copy is null
     */
    public Board(Board other) throws IlligalBoardStateException {
        this.width = other.width;
        this.height = other.height;
        this.board = new Tile[other.width][other.height];

        // and copy the owner
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                // make new tile
                board[row][col] = new Tile();

                // set id
                int id = (row)*width + col + 1;
                board[row][col].setId(id);

                // set owner
                Player owner = other.board[row][col].getOwner();
                board[row][col].setOwner(owner);
            }
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

    /**
     * Prints the board to the console for debugging.
     */
    public void debugPrint() {
        String toPrint = "";

        for (int row = 0; row < height; row++) {

            for (int col = 0; col < width; col++) {

                Player owner = board[row][col].getOwner();

                // add spacer
                if (col != 0) {
                    toPrint += "|";
                }

                if (owner == null) {
                    toPrint += "_";
                } else {
                    toPrint += owner.getName();
                }
            }
            toPrint += "\n";
        }
        System.out.println(toPrint);
    }
}

