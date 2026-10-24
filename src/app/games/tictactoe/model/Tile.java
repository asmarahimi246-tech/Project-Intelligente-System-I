package app.games.tictactoe.model;

import app.games.framework.player.Player;

/**
 * TIle
 */
public class Tile {
    public Player owner = null;

    /**
     * Sets the owner
     * @param owner owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets the owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Checks if the tile is empty
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        if (owner == null) {
            return true;
        } else {
            return false;
        }
    }
}
