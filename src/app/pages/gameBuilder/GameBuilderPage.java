package app.pages.gameBuilder;

import javax.swing.JPanel;

import app.pages.context.Page;

/**
 * GameBuilderPage
 * 
 * Contains the ui for building a game
 */
public class GameBuilderPage extends Page {
    private String gameMode;

    /**
     * Makes a game builder with the game mode
     * 
     * @param gameMode on of the following:
     *  online
     *  local
     *  tournament
     */
    public GameBuilderPage(String gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public void open() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'open'");
    }
    
}
