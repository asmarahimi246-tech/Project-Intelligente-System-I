package app.games.tictactoe;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import app.games.framework.exceptions.InvalidMoveException;
import app.games.framework.game.Game;
import app.games.framework.game.GameState;
import app.games.framework.move.Move;
import app.games.framework.player.Player;
import app.games.tictactoe.model.TicTacToeModel;
import app.games.tictactoe.model.TicTacToeMove;
import app.util.Config;
import app.util.Logger;

/**
 * connect View with Model
 * get input &amp; make Model do the work, then tell View what to display
 *
 * EX:
 * doMove()
 *   --make model check if move is valid
 *   --if true, make view update with the move added
 */
public class  TicTacToeGame extends Game {
    public TicTacToeModel model = new TicTacToeModel();
    private int moves = 0;

    /**
     * Makes a game
     * 
     * @param page the view
     * @param players the players
     * @param turnLimit how long a turn os
     */
    public TicTacToeGame(TicTacToePage page, ArrayList<Player> players, long turnLimit) {
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
        if (Config.X_AND_O) {
            if (player == players.get(0)) {
                return "X";
            }
            return "O";
        } else {
            String toReturn = "" + player.getName().toUpperCase().charAt(0);
            return toReturn;
        }
    }

    @Override
    public void run() {
        TicTacToePage view = (TicTacToePage) page;

        // game loop
        while (state.getStatus() == GameState.States.IN_PROGRESS | state.getStatus() == GameState.States.FORFEITED) {
            Logger.logDebug("[tic tac toe]");
            Logger.logDebug("new tic tac toe round");

            // each player does a move
            for (Player player : players) {
                moves++;
                Logger.logDebug("moves" + moves);

                // set the current player
                model.setCurrentPlayer(player);

                // check game states
                switch (state.getStatus()) {
                    case FORFEITED:
                        state = GameState.playerWon(player);
                        view.showResult(state.getDetail() + " win");
                    
                    case PLAYER_WON:
                        view.showResult(state.getDetail() + " win");
                        return;

                    case DRAW:
                        view.showResult("draw");
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
                model.placeMove((TicTacToeMove) move);

                // check for win
                if (model.checkForWin()) {
                    state = GameState.playerWon(model.getCurrentPlayer());
                }

                // check for draw
                if (moves >= 9) {
                    state = GameState.draw();
                }
                
            } catch (InvalidMoveException e) {
                state = GameState.failed(e);
            }
        }
    }
}

