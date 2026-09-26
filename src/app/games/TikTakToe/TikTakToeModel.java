package app.games.TikTakToe;

import java.util.ArrayList;

/**
 * TikTakToeModel
 */
public class TikTakToeModel {
    private char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * checks if the tile on the location is empty
     * 
     * @param location the location
     * @param player1 the char that player one uses
     * 
     * @return true if its empty else false
     */
    public boolean tileIsEmpty(int location, ArrayList<Character> tocheck) {
        boolean toReturn = true;
        for (char playerCHar : tocheck) {
            if (board[location] == playerCHar) {
                toReturn = false;
            }
        }
        return toReturn;
    }

    /**
     * gets the game board
     * 
     * @return the board
     */
    public char[] getBoard() {
        return this.board;
    }

    /**
     * places a char on the board
     * 
     * @param location the location
     * @param toSet the char
     */
    public void setTile(int location, char toSet) {
        board[location] = toSet;
    }
}
