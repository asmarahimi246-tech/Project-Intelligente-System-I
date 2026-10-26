package src.app.games.framework.player;

import java.util.function.Supplier;

/**
 * Player option with a label and a way to create it
 */
public record PlayerOption<P extends Player>(String label, Supplier<P> create) {
    /**
     * Makes a new player
     * 
     * @return the player
     */
    public P build() {
        return create.get();
    }
}