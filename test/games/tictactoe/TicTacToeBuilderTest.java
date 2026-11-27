package games.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import app.games.framework.game.GameMode;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerOption;
import app.games.tictactoe.TicTacToeBuilder;
import app.games.tictactoe.players.LocalPlayer;
import app.games.tictactoe.players.OnlineOpponent;
import app.games.tictactoe.players.OnlinePlayer;
import app.games.tictactoe.players.moveStrategies.AggresiveAIStrategy;
import app.games.tictactoe.players.moveStrategies.PassiveAIStrategy;
import app.games.tictactoe.players.moveStrategies.RandomAiStrategy;
import app.games.tictactoe.players.moveStrategies.UserStrategy;

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
        assertEquals(builder, builder.self());
    }


    /**
     * Checks if the builder returns the correct player options for LOCAL mode
     */
    @Test 
    void correctLocalPlayerOptions() {
        builder.setMode(GameMode.LOCAL);
        List<PlayerOption<? extends Player>> options = builder.getPlayerOptions();

        assertNotNull(options);
        assertEquals(4, options.size());

        // Verify instantiated player types and strategies (assuming create()/get() on PlayerOption and getStrategy() on Player)
        Player p0 = options.get(0).build();
        Player p1 = options.get(1).build();
        Player p2 = options.get(2).build();
        Player p3 = options.get(3).build();

        assertInstanceOf(LocalPlayer.class, p0);
        assertInstanceOf(UserStrategy.class, p0.getStrategy());

        assertInstanceOf(LocalPlayer.class, p1);
        assertInstanceOf(AggresiveAIStrategy.class, p1.getStrategy());

        assertInstanceOf(LocalPlayer.class, p2);
        assertInstanceOf(PassiveAIStrategy.class, p2.getStrategy());

        assertInstanceOf(LocalPlayer.class, p3);
        assertInstanceOf(RandomAiStrategy.class, p3.getStrategy());
    }

    /**
     * Checks if the builder returns the correct player options for ONLINE mode
     */
    @Test 
    void correctOnlinePlayerOptions() {
        builder.setMode(GameMode.ONLINE);
        List<PlayerOption<? extends Player>> options = builder.getPlayerOptions();

        assertNotNull(options);
        assertEquals(4, options.size());

        Player p0 = options.get(0).build();
        Player p1 = options.get(1).build();
        Player p2 = options.get(2).build();
        Player p3 = options.get(3).build();

        assertInstanceOf(OnlinePlayer.class, p0);
        assertInstanceOf(UserStrategy.class, p0.getStrategy());

        assertInstanceOf(OnlinePlayer.class, p1);
        assertInstanceOf(AggresiveAIStrategy.class, p1.getStrategy());

        assertInstanceOf(OnlinePlayer.class, p2);
        assertInstanceOf(PassiveAIStrategy.class, p2.getStrategy());

        assertInstanceOf(OnlinePlayer.class, p3);
        assertInstanceOf(RandomAiStrategy.class, p3.getStrategy());
    }

    /**
     * Checks if the builder returns the correct player options for TOURNAMENT mode
     */
    @Test 
    @Disabled("Skipping until client mocking is set up")
    void correctTournamentPlayerOptions() {
        builder.setMode(GameMode.TOURNAMENT);
        List<PlayerOption<? extends Player>> options = builder.getPlayerOptions();

        assertNotNull(options);
        assertEquals(4, options.size());

        Player p0 = options.get(0).build();
        Player p1 = options.get(1).build();
        Player p2 = options.get(2).build();
        Player p3 = options.get(3).build();

        // Verify types and strategies
        assertInstanceOf(OnlinePlayer.class, p0);
        assertInstanceOf(UserStrategy.class, p0.getStrategy());

        assertInstanceOf(OnlinePlayer.class, p1);
        assertInstanceOf(AggresiveAIStrategy.class, p1.getStrategy());

        assertInstanceOf(OnlinePlayer.class, p2);
        assertInstanceOf(PassiveAIStrategy.class, p2.getStrategy());

        assertInstanceOf(OnlinePlayer.class, p3);
        assertInstanceOf(RandomAiStrategy.class, p3.getStrategy());
    }

    /**
     * Checks if the builder has default local player options
     */
    @Test 
    void noDefaultLocalPlayers() {
        builder.setMode(GameMode.LOCAL);
        builder.addDefaultPlayers();

        // get the players
        List<PlayerOption<? extends Player>> players = builder.getPlayers(); 
        assertEquals(0, players.size());
    }

    /**
     * Checks if the builder adds the correct online default player options
     */
    @Test
    void addsRemotePlayerInOnlineMode() {
        builder.setMode(GameMode.ONLINE);

        // get the players
        List<PlayerOption<? extends Player>> players = builder.getPlayers(); 
        assertEquals(1, players.size());

        assertInstanceOf(OnlineOpponent.class, players.get(0).build());
    }

    /**
     * Checks if the builder adds the correct tournament default player options
     */
    @Test
    void addsRemotePlayerInTournamentMode() {
        builder.setMode(GameMode.TOURNAMENT);

        // get the players
        List<PlayerOption<? extends Player>> players = builder.getPlayers(); 
        assertEquals(1, players.size());

        assertInstanceOf(OnlineOpponent.class, players.get(0).build());
    }

    /**
     * Checks if the builder adds the correct tournament default player options
     * 
     * TODO: mock de client
     */
    @Test
    @Disabled("Skipping until client mocking is set up")
    void onlinePlayerInTournamentModeHasCorrectName() {
        throw new UnsupportedOperationException();
    }
}
