package app.pages;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.games.framework.Game;
import app.games.framework.GameBuilder;
import app.pages.context.Page;
import app.pages.context.PageManager;

/**
 * GamePage
 * 
 * The page where you play a game
 */
public class GamePage<G extends Game, B extends GameBuilder<G, B>> extends Page {
    private final B builder;
    private final Game game;

    @Override
    public String toString() {
        String toReturn = "game page";
        return toReturn;
    }

    /**
     * Constructor
     * 
     * Makes a game page
     * @param builder
     */
    public GamePage (B builder) {
        this.builder = builder;

        // get the game
        Game game = builder.build();
        this.game = game;
    }

    @Override
    public void createSwingUi() {
        // TODO: ui surounding the page
        // the game page is only a widget and contain only the minimal needed for a game
        // exampe:
        // tic tac toe only has a 3x3 grid with X and O

        // check if the page  exists
        PageManager manager = PageManager.getInstance();
        Page page = game.getPage();
        if (page == null) {
            Page main = new MainPage();
            manager.setCurrentPage(main);
        }

        // add the page
        add(page);
    }

    @Override
    public void open() {
        super.open();
        game.run();

        // return to main menu when the game ends
        PageManager manager = PageManager.getInstance();
        Page main = new MainPage();
        manager.setCurrentPage(main);
    }

    @Override
    public void createCLI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCLI'");
    }
}
