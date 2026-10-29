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
    private Thread gameThread;

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
        removeAll();
        setLayout(new GridLayout(1, 1));
        // the game page is only a widget and contain only the minimal needed for a game
        // exampe:
        // tic tac toe only has a 3x3 grid with X and O
        if (game == null) {
            // add a loading page
            LoadingPage loading = new LoadingPage("waiting for match...");
            loading.open();
            add(loading);
            Logger.logDebug("waiting for match...");
        } else {
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
    }

    @Override
    public void open() {
        // this is not how one does this it needs a good interupt
        // it currently only closes the thread when completely exiting
        // the application going to main only hides it
        // TODO: add interupt
        Runnable gameRunnable = () -> {
            // repeatidly rebuild until the a page change is requested
            while (true) {
                game = null;
                super.open();
                Logger.logDebug("starting new tournament round");

                // get the game
                game = builder.build();
                super.open();

                // then build the content view
                game.run();
                Logger.logDebug("tournament round end");
            }
        };

        gameThread = new Thread(gameRunnable);      

        gameThread.start();

        if (Config.SWING_SUPPORTED) {
            return;
        }
    }

    @Override
    public void createCLI() {
        // im very usefull
    }

    @Override
    public void close() {
        // close the game
        Logger.logDebug("closing tournament");
        gameThread.interrupt();
    }
}
