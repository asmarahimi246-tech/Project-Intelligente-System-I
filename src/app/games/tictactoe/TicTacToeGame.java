package app.games.tictactoe;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import app.games.framework.game.Game;
import app.games.framework.move.Move;
import app.games.framework.player.Player;
import app.games.tictactoe.model.TicTacToeModel;
import app.games.tictactoe.model.TicTacToeMove;
import app.util.Config;

/**
 * <pre>
 * &gt; connect View with Model
 * &gt; get input &amp; make Model do the work, then tell View what to display
 *
 * EX:
 * &gt; doMove()
 *   --&gt; make model check if move is valid
 *   --&gt; if true, make view update with the move added
 * </pre>
 */
public class TicTacToeGame extends Game {
    public TicTacToeModel model = new TicTacToeModel();

    /**
     * Makes a game
     * 
     * @param page the view
     * @param players the players
     */
    public TicTacToeGame(TicTacToePage page, ArrayList<Player> players) {
        super(page, players);

        if (Config.X_AND_O) {
            // add x and o to the player names
            Player x = super.players.get(0);
            String oldX = x.getName();
            String newX = "x" + oldX;
            super.players.get(0).setName(newX);
        
            Player o = super.players.get(1);
            String oldO = o.getName();
            String newO = "o" + oldO;
            super.players.get(1).setName(newO);
        }
        
        super.running = true;
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

    public String getSymbol(Player player) {
        if (player == players.get(0)) {
            return "X";
        }

        return "O";
    }

    /**    (non-Javadoc)
     * TODO: put print statements in page
     * @see app.games.framework.game.Game#run()
     */
    @Override
    public void run() {
        int moves = 0;
        TicTacToePage view = (TicTacToePage) page;

        // game loop
        while (running) {

            // each player does a move
            for (Player player : players) {
                // set the current player
                model.setCurrentPlayer(player);
                
                if (Config.SWING_SUPPORTED) {
                    SwingUtilities.invokeLater(() -> page.open());
                } else {
                    page.open();
                }

                player.doMove(this);


                if (Config.DEBUG) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                // check for win
                if (model.checkForWin()) {
                    running = false;
                    view.showResult(getSymbol(player) + " wins!");
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
