package config;

import games.common.game.GameController;
import games.common.game.GameModel;
import games.common.game.GameView;
import games.tictactoe.TicTacToeController;
import games.tictactoe.TicTacToeModel;
import games.tictactoe.TicTacToeView;
import org.jetbrains.annotations.NotNull;


public class GameFactory {
    private static GameModel model;
    private static GameView view;
    /**
     * Make a GameController of the given GameType
     * @return a new GameController of the given game
     */
    public GameController makeController(@NotNull GameConfig config) {
        return switch (config.getType()) {
            case TIC_TAC_TOE -> {
                GameModel model = new TicTacToeModel();
                GameView view = new TicTacToeView();

                yield new TicTacToeController(model, view);

            // new games go here
            }
        };
    }

    public GameView getView() { return view; }

    public GameModel getModel() { return model; }
}