package app.pages;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.pages.context.Page;

/**
 * GamePage
 * 
 * The page where you play a game
 */
public class GamePage extends  Page {
    @Override
    public String toString() {
        String toReturn = "game page";
        return toReturn;
    }

    public GamePage () {
        super();
        add(new JLabel(toString()));
    }

    @Override
    public void open() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'open'");
    }
}
