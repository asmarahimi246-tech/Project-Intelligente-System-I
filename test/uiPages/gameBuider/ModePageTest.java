package uiPages.gameBuider;

import static org.junit.jupiter.api.Assertions.*;

import app.uiPages.gameBuilder.ModePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.TicTacToeBuilder;
import app.games.framework.game.GameMode;
import app.uiPages.context.widgets.OptionMenu;
import app.uiPages.context.widgets.Button;

public class ModePageTest {

    private TicTacToeBuilder builder;
    private ModePage<TicTacToeGame, TicTacToeBuilder> modePage;

    @BeforeEach
    void setUp() {
        builder = new TicTacToeBuilder();
        modePage = new ModePage<>(builder);
    }

    @Test
    void testToString() {
        assertEquals("mode page", modePage.toString());
    }

    @Test
    void testModePageIsNotNull() {
        assertNotNull(modePage);
    }

    @Test
    void testConstructor() {
        assertNotNull(builder);
        assertNotNull(modePage);
    }

    @Test
    void testLocalButton() {
        // Maak de Swing-interface
        modePage.createSwingUi();

        // Haal het menu op
        OptionMenu menu = (OptionMenu) modePage.getComponent(0);

        // Haal de Local game-knop op
        Button localButton = (Button) menu.getComponent(1);

        // Klik op de knop
        localButton.doClick();

        // Controleer of de juiste gamemode is ingesteld
        assertEquals(GameMode.LOCAL, builder.getMode());
    }
}
