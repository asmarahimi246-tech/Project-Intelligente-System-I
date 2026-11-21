package uiPages.context.widgets;


import static org.junit.jupiter.api.Assertions.*;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import app.uiPages.context.widgets.Button;
import app.uiPages.context.widgets.OptionMenu;
import org.junit.jupiter.api.Test;

class OptionMenuTest {

    //Wordt het menu aangemaakt?
    @Test
    void testOptionMenuIsNotNull() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ArrayList<Button> options = new ArrayList<>();

            OptionMenu menu = new OptionMenu(
                    options,
                    "Test Menu"
            );

            assertNotNull(menu);
        });
    }

    //Heeft het menu de juiste titel?
    @Test
    void testMenuTitle() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ArrayList<Button> options = new ArrayList<>();

            OptionMenu menu = new OptionMenu(
                    options,
                    "Test Menu"
            );

            JLabel title = (JLabel) menu.getComponent(0);

            assertEquals("Test Menu", title.getText());
        });
    }


//Worden alle knoppen toegevoegd?
    @Test
    void testNumberOfButtons() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ArrayList<Button> options = new ArrayList<>();

            options.add(new Button(event -> {}, "Button 1"));
            options.add(new Button(event -> {}, "Button 2"));
            options.add(new Button(event -> {}, "Button 3"));

            OptionMenu menu = new OptionMenu(
                    options,
                    "Test Menu"
            );

            // 3 buttons + 1 title
            assertEquals(4, menu.getComponentCount());
        });
    }

    //Staan de juiste knoppen in het menu?
    @Test
    void testButtonsAreAdded() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ArrayList<Button> options = new ArrayList<>();

            Button button1 = new Button(event -> {}, "Button 1");
            Button button2 = new Button(event -> {}, "Button 2");

            options.add(button1);
            options.add(button2);

            OptionMenu menu = new OptionMenu(
                    options,
                    "Test Menu"
            );

            assertSame(button1, menu.getComponent(1));
            assertSame(button2, menu.getComponent(2));
        });
    }

    //Heeft het menu de juiste GridLayout?
    @Test
    void testGridLayout() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ArrayList<Button> options = new ArrayList<>();

            options.add(new Button(event -> {}, "Button 1"));
            options.add(new Button(event -> {}, "Button 2"));

            OptionMenu menu = new OptionMenu(
                    options,
                    "Test Menu"
            );

            GridLayout layout = (GridLayout) menu.getLayout();

            assertEquals(3, layout.getRows());
            assertEquals(1, layout.getColumns());
            assertEquals(10, layout.getHgap());
            assertEquals(10, layout.getVgap());
        });
    }
}

