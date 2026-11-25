package app.games.reversi;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import app.games.framework.exceptions.InvalidMoveException;
import app.games.framework.game.Game;
import app.games.framework.game.GameState;
import app.games.framework.move.Move;
import app.games.framework.player.Player;
import app.games.reversi.model.ReversiModel;
import app.games.reversi.model.ReversiMove;
import app.games.tictactoe.model.TicTacToeMove;
import app.util.Config;
import app.util.Logger;

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
     * @param turnLimit the turn limit
     */
    public ReversiGame(ReversiPage page, ArrayList<Player> players, long turnLimit) {
        super(page, players, turnLimit);
        super.state = GameState.inProgress();
    }

    /**
     * Returns a player symbol
     * 
     * @param player the player
     * 
     * @return the players symbol
     */
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
        while (state.getStatus() == GameState.States.IN_PROGRESS | state.getStatus() == GameState.States.FORFEITED) {
            Logger.logDebug("[reversi]");
            Logger.logDebug("new reversi round");

            // each player does a move
            for (Player player : players) {

                // set the current player
                model.setCurrentPlayer(player);

                // check game states
                switch (state.getStatus()) {
                    case FORFEITED:
                        state = GameState.playerWon(player);
                        //view.showResult(state.getDetail() + " win");
                    
                    case PLAYER_WON:
                        //view.showResult(state.getDetail() + " win");
                        return;

                    case DRAW:
                        //view.showResult("draw");
                        return;

                    case IN_PROGRESS:
                        break;

                    default:
                        break;
                }

                
                if (Config.SWING_SUPPORTED) {
                    SwingUtilities.invokeLater(() -> page.open());
                } else {
                    page.open();
                }

                Logger.logDebug("start move");
                player.doMove(this);
            }
        }
    }

    @Override
    public void procesMove(Move move) {
        if (move instanceof TicTacToeMove) {
            try {
                //model.placeMove((TicTacToeMove) move);

                // check for win
                //if (model.checkForWin()) {
                //    state = GameState.playerWon(model.getCurrentPlayer());
                //}

                // check for draw
                //if (moves >= 9) {
                //    state = GameState.draw();
                //}
                
            } catch (InvalidMoveException e) {
                state = GameState.failed(e);
            }
        }
    }
}
