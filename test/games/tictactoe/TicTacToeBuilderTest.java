package games.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.games.tictactoe.TicTacToeBuilder;

/**
 * TicTacToeBuilderTest
 */
public class TicTacToeBuilderTest {
    private TicTacToeBuilder builder = null;
    
    /**
     * Resets the builder
     */
    @BeforeEach 
    private void resetBuilder() {
        builder = new TicTacToeBuilder();
    }

    /**
     * Check if the name is correct
     */
    @Test
    void builderHasExpectedName() {
        String name = builder.toString();
        String correct = "tic-tac-toe Builder";
        assertEquals(correct, name);
    }

    /**
     * Checks if the builder returns itself
     */
    @Test 
    void builderReturnsSelf() {
        //TODO:
    }

    /**
     * Checks if the builder returns the correct player options
     */
    @Test 
    void correctLocalPlayerOptions() {
        //TODO:
    }

    /**
     * Checks if the builder returns the correct player options
     */
    @Test 
    void correctOnlinePlayerOptions() {
        //TODO:
    }

    /**
     * Checks if the builder returns the correct player options
     */
    @Test 
    void correctTournamentPlayerOptions() {
        //TODO:
    }

    /**
     * Checks if the builder adds the correct default player options
     */
    @Test 
    void correctDefaultLocalPlayers() {
        //TODO:
    }

    /**
     * Checks if the builder adds the correct default player options
     */
    @Test 
    void correctDefaultOnlinePlayers() {
        //TODO:
    }

    /**
     * Checks if the builder adds the correct default player options
     */
    @Test 
    void correctDefaultTournamentPlayers() {
        //TODO:
    }
}
