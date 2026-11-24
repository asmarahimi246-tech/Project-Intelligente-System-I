package app.games.tictactoe;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import app.games.framework.game.Game;
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

    /**
     * Makes a game
     * 
     * @param page the view
     * @param players the players
     */
    public TicTacToeGame(TicTacToePage page, ArrayList<Player> players) {
        super(page, players);
        
        super.running = true;
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
        int moves = 0;
        TicTacToePage view = (TicTacToePage) page;

        // game loop
        while (running) {
            Logger.logDebug("[tic tac toe]");
            Logger.logDebug("new tic tac toe round");

            // each player does a move
            for (Player player : players) {
                // set the current player
                model.setCurrentPlayer(player);
                
                if (Config.SWING_SUPPORTED) {
                    SwingUtilities.invokeLater(() -> page.open());
                } else {
                    page.open();
                }

                Logger.logDebug("start move");
                player.doMove(this);

                if (!running) {
                    break;
                }

                // check for win
                if (model.checkForWin()) {
                    running = false;
                    view.showResult(player.getName() + " wins!");
                    break;
                }

                // check for draw
                moves++;
                if (moves == 9) {
                    running = false;
                    view.showResult("Draw!");
                    break;
                };
            }
        }
    }

    @Override
    public void procesMove(Move move) {
        if (move instanceof TicTacToeMove) {
            model.placeMove((TicTacToeMove) move);
        }
    }
}
