package app.games.framework.player;

import java.util.function.Supplier;

/**
 * Player option with a label and a way to create it
 * 
 * @param <P> the type of player this creates
 * @param label is printed in the option list
 * @param create a supplier that creates a player of type {@code P}
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