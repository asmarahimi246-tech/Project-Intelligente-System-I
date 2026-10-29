package app.uiPages;

import java.awt.GridLayout;

import app.games.framework.game.Game;
import app.games.framework.game.GameBuilder;
import app.games.framework.game.GameMode;
import app.uiPages.context.Page;
import app.uiPages.context.PageManager;
import app.util.Config;
import app.util.Logger;

/**
 * TournamentPage
 */
public class TournamentPage<G extends Game, B extends GameBuilder<G, B>> extends Page {
    private final B builder;
    private Game game = null;

    @Override
    public String toString() {
        String toReturn = "tournament page";
        return toReturn;
    }

    /**
     * Constructor
     * 
     * Makes a game page
     * 
     * @param builder the builder of the game
     */
    public TournamentPage (B builder) {
        this.builder = builder;
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
        PageManager manager = PageManager.getInstance();

        // repeatidly rebuild until the a page change is requested
        while (!manager.pageChangeRequested()) {
            Logger.logDebug("starting new tournament round");
            removeAll();

            // get the game
            game = builder.build();

            // build this view first
            super.open();

            // then build the content view
            game.run();
            Logger.logDebug("tournament round end");

            revalidate();
        }

        if (Config.SWING_SUPPORTED) {
            return;
        }

        // return to main menu when the game ends
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
        Logger.logDebug("closing tournament");
        game.close();
        builder.close();
    }
}
