package games.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.games.framework.player.Player;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.TicTacToePage;
import app.games.tictactoe.players.LocalPlayer;

import java.util.ArrayList;

import javax.swing.JButton;

/**
 * Tests for the tictactoe board GUI
 */
public class TicTacToePageTest {
    private TicTacToeGame game;
    private TicTacToePage page;
    private LocalPlayer playerX;
    private LocalPlayer playerO;

    /**
     * Make a new game and page before each test
     */
    @BeforeEach
    void makeGameAndPage() {
        playerX = new LocalPlayer(null);
        playerO = new LocalPlayer(null);

        ArrayList<Player> players = new ArrayList<>();
        players.add(playerX);
        players.add(playerO);

        page = new TicTacToePage();
        game = new TicTacToeGame(page, players, 100);
        page.setGame(game);
    }

    /**
     * Board should have 9 buttons
     */
    @Test
    void boardHasNineButtons() {
        page.createSwingUi();

        assertEquals(9, page.getComponentCount());
    }

    /**
     * Buttons should start empty
     */
    @Test
    void buttonsStartEmpty() {
        page.createSwingUi();

        for (int i = 0; i < 9; i++) {
            JButton button = (JButton) page.getComponent(i);
            assertEquals("", button.getText());
        }
    }

    /**
     * Buttons should be disabled at the start
     */
    @Test
    void buttonsStartDisabled() {
        page.createSwingUi();

        for (int i = 0; i < 9; i++) {
            JButton button = (JButton) page.getComponent(i);
            assertFalse(button.isEnabled());
        }
    }

    /**
     * Owned squares should show X or O
     * 
     * Config.X_AND_O must be set to true, otherwise getSymbol shows
     * the first letter of the player's name
     */
    @Test
    void ownedSquaresShowXAndO() {
        game.model.getBoard().getByPos(0, 0).setOwner(playerX);
        game.model.getBoard().getByPos(2, 2).setOwner(playerO);

        page.createSwingUi();

        JButton topLeft = (JButton) page.getComponent(0);
        JButton bottomRight = (JButton) page.getComponent(8);
        assertEquals("X", topLeft.getText());
        assertEquals("O", bottomRight.getText());
    }
}
