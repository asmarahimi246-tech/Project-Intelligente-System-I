package games.tictactoe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.games.framework.game.GameMode;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.PlayerOption;
import app.games.tictactoe.TicTacToeBuilder;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.model.Board;
import app.games.tictactoe.model.TicTacToeMove;
import app.games.tictactoe.players.LocalPlayer;
import app.games.tictactoe.players.moveStrategies.*;

/**
 * MoveStrategiesTest
 */
public class MoveStrategiesTest {
    TicTacToeGame game = null;

    /**
     * Make a new game before running the tests
     * 
     * Has 2 local players that do nothing
     */
    @BeforeAll 
    void makeGame () {
        TicTacToeBuilder builder = new TicTacToeBuilder();
        // build the game
        game = (TicTacToeGame) builder
            .setMode(GameMode.LOCAL)
            .addPlayer(new PlayerOption<LocalPlayer>("player", () -> new LocalPlayer(null)))
            .addPlayer(new PlayerOption<LocalPlayer>("player", () -> new LocalPlayer(null)))
            .build();
    }

    /**
     * Test if the aggresive ai correctly does a move
     */
    @Test
    void aggresiveAi() {
        Board board = new Board();

        MoveSrategy aggresive = new AggresiveAIStrategy();
        TicTacToeMove move = (TicTacToeMove) aggresive.getMove(null);
    }
}