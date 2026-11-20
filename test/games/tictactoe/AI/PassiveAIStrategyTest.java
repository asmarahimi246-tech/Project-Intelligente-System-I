package games.tictactoe.AI;

import app.games.tictactoe.model.TicTacToeMove;
import app.games.tictactoe.players.moveStrategies.PassiveAIStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassiveAIStrategyTest {

    private final PassiveAIStrategy strategy = new PassiveAIStrategy();

    @Test
    void testWinningMove() {
        char[] board = {
                'X', 'X', '_',
                'O', 'O', '_',
                '_', '_', '_'
        };

        TicTacToeMove move = strategy.calculateBestMove(board, 'X');

        assertEquals(0, move.row());
        assertEquals(2, move.column());
    }

    @Test
    void testBlockingOpponent() {
        char[] board = {
                'O', 'O', '_',
                'X', '_', '_',
                'X', '_', '_'
        };

        TicTacToeMove move = strategy.calculateBestMove(board, 'X');

        assertEquals(0, move.row());
        assertEquals(2, move.column());
    }

    @Test
    void testOnlyOneEmptyPosition() {
        char[] board = {
                'X', 'O', 'X',
                'O', 'X', 'O',
                'O', 'X', '_'
        };

        TicTacToeMove move = strategy.calculateBestMove(board, 'X');

        assertEquals(2, move.row());
        assertEquals(2, move.column());
    }
}
