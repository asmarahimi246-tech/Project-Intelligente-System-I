package games.tictactoe.AI;

import app.games.framework.components.Board;
import app.games.framework.move.MoveSrategy;
import app.games.tictactoe.model.TicTacToeMove;
import app.games.tictactoe.players.LocalPlayer;
import app.games.tictactoe.players.moveStrategies.AggresiveAIStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AggresiveAIStrategyTest {
    private final LocalPlayer currentPlayer= new LocalPlayer(null);
    private final LocalPlayer opponent = new LocalPlayer(null);
    private final AggresiveAIStrategy strategy = new AggresiveAIStrategy();

    /**
     * Helper to make a board from a player array
     * 
     * @param alreadyOwned an array with already owned tiles
     * 
     * @return the board
     */
    private Board boardFromArray(LocalPlayer[] alreadyOwned) {
        Board board = new Board(3, 3);
        for (int i = 0; i < 9; i++) {
            board.getById(i + 1).setOwner(alreadyOwned[i]);
        }
        return board;
    }

    /**
     * Test if the aggresive ai correctly attacks the following board
     * X|X|_
     * O|O|_
     * X|_|_
     */
    @Test
    void testWinningMoveOverDefense() {
        // make a board to test with
        LocalPlayer[] alreadyOwned = new LocalPlayer[] {opponent, opponent, null
                                                        ,currentPlayer, currentPlayer, null
                                                        ,opponent, null, null
                                                        };
        Board board = boardFromArray(alreadyOwned);

        TicTacToeMove move = strategy.getMove(board, currentPlayer);

        assertEquals(1, move.row());
        assertEquals(2, move.column());
    }

    /**
     * Test if the aggresive ai correctly defends the following board
     * X|X|_
     * _|O|_
     * _|_|_
     */
    @Test
    void testBlockingOpponent() {
        // make a board to test with
        LocalPlayer[] alreadyOwned = new LocalPlayer[] {opponent, opponent, null
                                                        ,null, currentPlayer, null
                                                        ,null, null, null
                                                        };
        Board board = boardFromArray(alreadyOwned);

        TicTacToeMove move = strategy.getMove(board, currentPlayer);

        assertEquals(0, move.row());
        assertEquals(2, move.column());
    }

    /**
     * Test if the aggresive ai correctly places the last tile
     * X|O|X
     * O|X|O
     * O|X|_
     */
    @Test
    void testOnlyOneEmptyPosition() {
        // make a board to test with
        LocalPlayer[] alreadyOwned = new LocalPlayer[] {opponent, currentPlayer, opponent
                                                        , currentPlayer, opponent, currentPlayer
                                                        , currentPlayer, opponent, null
                                                        };
        Board board = boardFromArray(alreadyOwned);

        TicTacToeMove move = strategy.getMove(board, currentPlayer);

        assertEquals(2, move.row());
        assertEquals(2, move.column());
    }

    /**
     * Test if the aggresive ai makes a trap
     * O|O|X
     * X|_|_
     * _|_|_
     */
    @Test
    void aggresiveAiTrap() {
        // make a board to test with
        LocalPlayer[] alreadyOwned = new LocalPlayer[] {currentPlayer, currentPlayer, null
                                                        ,opponent, null, opponent
                                                        ,null, null, null
                                                        };
        Board board = boardFromArray(alreadyOwned);
        
        TicTacToeMove move = strategy.getMove(board, currentPlayer);

        assertEquals(1, move.row());
        assertEquals(1, move.column());
    }
}
