package app.games.reversi;

import java.util.ArrayList;

import app.games.framework.game.Game;
import app.games.framework.game.GameState;
import app.games.framework.move.Move;
import app.games.framework.player.Player;
import app.games.reversi.model.ReversiModel;
import app.games.reversi.model.ReversiMove;
import app.uiPages.context.Page;
import app.util.Config;
import app.util.Logger;

import javax.swing.*;

/**
 * ReversiGame
 */
public class ReversiGame extends Game {
    public ReversiModel model = new ReversiModel();

    /**
     * Makes a game
     * 
     * @param page the view
     * @param players the players
     */
    public ReversiGame(ReversiPage page, ArrayList<Player> players, long turnLimit) {
        super(page, players, turnLimit);
        super.state = GameState.inProgress();
    }

    public String getSymbol(Player player) {
        if (Config.W_AND_B) {
            if (player == players.get(0)) {
                return "B";
            }
            return "W";
        } else {
            String toReturn = "" + player.getName().toUpperCase().charAt(0);
            return toReturn;
        }
    }

    @Override
    public void run() {
        // sets the starting position of the board with 4 symbols in the middle
        model.setCurrentPlayer(players.get(1));
        model.placeMove(new ReversiMove(3, 3));
        model.placeMove(new ReversiMove(4, 4));

        model.setCurrentPlayer(players.get(0));
        model.placeMove(new ReversiMove(3, 4));
        model.placeMove(new ReversiMove(4, 3));

        // game loop
        while (state.getStatus() == GameState.States.IN_PROGRESS) {
            Logger.logDebug("[reversi]");
            Logger.logDebug("new reversi round");

            // each player does a move
            for (Player player : players) {
                model.setCurrentPlayer(player);
                // set the current player
                if (Config.SWING_SUPPORTED) {
                    SwingUtilities.invokeLater(() -> page.open());
                } else {
                    page.open();
                }

                Logger.logDebug("start move");
                player.doMove(this);

                if (state.getStatus() != GameState.States.IN_PROGRESS) {
                    break;
                }

                // check for win
//                if (model.checkForWin()) {
//                    running = false;
//                    view.showResult(player.getName() + " wins!");
//                    break;
//                }

                // check for draw
//                moves++;
//                if (moves == 9) {
//                    running = false;
//                    view.showResult("Draw!");
//                    break;
//                }
            }
        }
    }

    @Override
    public void procesMove(Move move) {
        if (move instanceof ReversiMove) {
            model.placeMove((ReversiMove) move);
        }
    }
}
