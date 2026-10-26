package app.pages;

import java.awt.GridLayout;

import app.games.framework.game.Game;
import app.games.framework.game.GameBuilder;
import app.pages.context.Page;
import app.pages.context.PageManager;

import app.util.Config;
import app.util.Logger;

/**
 * GamePage
 * 
 * The page where you play a game
 * 
 * @param <G> the type of game to show
 * @param <B> the builder of the game
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
     * 
     * @param builder the builder of the game
     */
    public GamePage (B builder) {
        this.builder = builder;

        // get the game
        Game game = builder.build();
        this.game = game;
    }

    @Override
    public void createSwingUi() {
        setLayout(new GridLayout(1, 1));
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
        // build this view first
        super.open();

        // then build the content view
        game.run();

        if (Config.SWING_SUPPORTED) {
            return;
        }

        // return to main menu when the game ends
        PageManager manager = PageManager.getInstance();
        Page main = new MainPage();
        manager.setCurrentPage(main);
    }

    @Override
    public void createCLI() {
        // im very usefull
    }

    @Override
    public void close() {
        // close the game
        game.close();
    }
}
