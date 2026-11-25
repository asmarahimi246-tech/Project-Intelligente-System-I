package uiPages.context.widgets;



import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingUtilities;

import app.uiPages.context.widgets.Button;
import org.junit.jupiter.api.Test;

class ButtonTest {
    //Heeft de knop de juiste tekst?

    @Test
    void testButtonText() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            Button button = new Button(
                    event -> {},
                    "Test Button"
            );

            assertEquals("Test Button", button.getText());
        });
    }

    //Wordt de ActionListener uitgevoerd wanneer je klikt?
    @Test
    void testButtonClick() throws Exception {
        AtomicBoolean clicked = new AtomicBoolean(false);

        SwingUtilities.invokeAndWait(() -> {
            Button button = new Button(
                    event -> clicked.set(true),
                    "Click me"
            );

            button.doClick();

            assertTrue(clicked.get());
        });
    }

    //Wordt de knop correct aangemaakt?
    @Test
    void testButtonNotNull() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            Button button = new Button(
                    event -> {},
                    "Test"
            );

            assertNotNull(button);
        });
    }
}

