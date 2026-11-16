package games.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.games.framework.game.GameMode;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
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
    LocalPlayer currentPlayer = null;
    LocalPlayer opponent = null;

    /**
     * Helper to make a board from a player array
     * 
     * @param alreadyOwned an array with already owned tiles
     * 
     * @return the board
     */
    private Board boardFromArray(LocalPlayer[] alreadyOwned) {
        Board board = new Board();
        for (int i = 0; i < 9; i++) {
            board.getById(i + 1).setOwner(alreadyOwned[i]);
        }
        return board;
    }

    /**
     * Prints the current board
     */
    private void printBoard() {
        Board board = game.model.getBoard();
        String toPrint = "";

        // loop trough all tiles
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // get the owner of a tile
                Player owner = board.getByPos(row, col).getOwner();

                // add spacer
                if (col != 0) {
                    toPrint += "|";
                }

                // check if its null
                if (owner == null) {
                    // get id
                    int id = (row)*3 + col + 1;
                    toPrint += "" + id;
                } else {
                    // Place first char of name if or x
                    toPrint += game.getSymbol(owner);
                }
            }
            toPrint += "\n";
        }
        System.out.println(toPrint);
    }

    /**
     * Make a new game before running the tests
     * 
     * Has 2 local players that do nothing
     */
    @BeforeEach
    void makeGame () {
        TicTacToeBuilder builder = new TicTacToeBuilder();

        // make the current player
        currentPlayer = new LocalPlayer(null);
        currentPlayer.setName("current player");

        // make the opponent
        opponent = new LocalPlayer(null);
        opponent.setName("opponent");

                // build the game
        game = (TicTacToeGame) builder
            .setMode(GameMode.LOCAL)
            .addPlayer(new PlayerOption<LocalPlayer>("player", () -> currentPlayer))
            .addPlayer(new PlayerOption<LocalPlayer>("player", () -> opponent))
            .prepare()
            .build();

        game.model.setCurrentPlayer(currentPlayer);
    }

    /**
     * NOTE: we are only testing legal game states
     */

    /**
     * Test if the random ai correctly defends the following board
     * X|X|_
     * _|O|_
     * _|_|_
     */
    @Test
    void randomAiDefenseA() {
        // make a board to test with
        LocalPlayer[] alreadyOwned = new LocalPlayer[] {opponent, opponent, null
                                                        ,null, currentPlayer, null
                                                        ,null, null, null
                                                        };
        Board board = boardFromArray(alreadyOwned);
        game.model.setBoard(board);
        
        // get a move
        MoveSrategy random = new RandomAiStrategy();
        TicTacToeMove move = (TicTacToeMove) random.getMove(game);
        game.procesMove(move);

        // assert that the move is the correct one for this board
        printBoard();
        TicTacToeMove correctMove = new TicTacToeMove(0, 2);
        assertEquals(correctMove, move);
    }

    /**
     * Test if the passive ai correctly defends the following board
     * X|X|_
     * _|O|_
     * _|_|_
     */
    @Test
    void passiveAiDefenseA() {
        // make a board to test with
        LocalPlayer[] alreadyOwned = new LocalPlayer[] {opponent, opponent, null
                                                        ,null, currentPlayer, null
                                                        ,null, null, null
                                                        };
        Board board = boardFromArray(alreadyOwned);
        game.model.setBoard(board);
        
        // get a move
        MoveSrategy passive = new PassiveAIStrategy();
        TicTacToeMove move = (TicTacToeMove) passive.getMove(game);
        game.procesMove(move);

        // assert that the move is the correct one for this board
        printBoard();
        TicTacToeMove correctMove = new TicTacToeMove(0, 2);
        assertEquals(correctMove, move);
    }

    /**
     * Test if the aggresive ai correctly defends the following board
     * X|X|_
     * _|O|_
     * _|_|_
     */
    @Test
    void aggresiveAiDefenseA() {
        // make a board to test with
        LocalPlayer[] alreadyOwned = new LocalPlayer[] {opponent, opponent, null
                                                        ,null, currentPlayer, null
                                                        ,null, null, null
                                                        };
        Board board = boardFromArray(alreadyOwned);
        game.model.setBoard(board);
        
        // get a move
        MoveSrategy aggresive = new AggresiveAIStrategy();
        TicTacToeMove move = (TicTacToeMove) aggresive.getMove(game);
        game.procesMove(move);

        // assert that the move is the correct one for this board
        printBoard();
        TicTacToeMove correctMove = new TicTacToeMove(0, 2);
        assertEquals(correctMove, move);
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
        game.model.setBoard(board);
        
        // get a move
        MoveSrategy aggresive = new AggresiveAIStrategy();
        TicTacToeMove move = (TicTacToeMove) aggresive.getMove(game);
        game.procesMove(move);

        // assert that the move is the correct one for this board
        printBoard();
        TicTacToeMove correctMove = new TicTacToeMove(1, 1);
        assertEquals(correctMove, move);
    }
}