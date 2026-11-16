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
 * GamePage
 * 
 * The page where you play a game
 * 
 * @param <G> the type of game to show
 * @param <B> the builder of the game
 */
public class GamePage<G extends Game, B extends GameBuilder<G, B>> extends Page {
    private final B builder;
    private Game game = null;
    private volatile Thread gameThread = null;
    private boolean running = true;

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
        builder.prepare();

        gameThread = null;

        //TODO: overleggen
        //idee dit

        // run in tournament mode
        Runnable gameRunnable = () -> {
            // repeatidly rebuild until the a page change is requested
            while (running) {
                game = null;
                super.open();
                Logger.logDebug("=============================");
                Logger.logDebug("starting new game session");
                Logger.logDebug("=============================");
                    
                // get the game
                try {
                    game = builder.build();
                } catch (RuntimeException e) {
                    if (Thread.currentThread().isInterrupted()) {
                        Logger.logDebug("Builder interrupted");
                        builder.close();
                        return;
                    }
                    throw e;
                }

                if (!running) {
                    break;
                }

                super.open();

                // then build the content view
                try {
                    game.run();
                } catch (RuntimeException e) {
                    if (Thread.currentThread().isInterrupted()) {
                        Logger.logDebug("Game interrupted");
                        game.close();
                        // dont close the builder here it wil give a bug
                        // and its not needed
                        return;
                    }
                    throw e;
                }
                Logger.logDebug("tournament round end");

                // tournament loops infinitly
                if (builder.getMode() != GameMode.TOURNAMENT || !running) {
                    break;
                }
                Logger.logDebug("=============================");
                Logger.logDebug("starting new tournament round");
                Logger.logDebug("=============================");
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
        running = false;
        // close the game
        game.close();
        builder.close();
        gameThread.interrupt();
    }
}
