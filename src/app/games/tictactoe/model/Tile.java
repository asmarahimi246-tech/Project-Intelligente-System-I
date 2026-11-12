package app.games.tictactoe.model;

import app.games.framework.player.Player;
import app.util.Logger;

/**
 * TIle
 */
public class Tile {
    /** The owner of the tile */
    public Player owner = null;
    /** The tile ID */
    public int id;

    @Override
    public String toString() {
        return "" + id;
    }

    /**
     * Sets the owner
     * @param owner owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets the owner
     * 
     * @return the owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the id
     * @param id owner
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID
     * 
     * @return the ID
     */
    public int getId() {
        return id;
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
