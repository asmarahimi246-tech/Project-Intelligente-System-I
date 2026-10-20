package app.games.tictactoe.players;

import app.games.framework.Player;
import app.games.framework.PlayerState;
import app.games.tictactoe.players.moveStrategies.Move;
import app.games.tictactoe.players.moveStrategies.MoveSrategy;

/**
 * LocalPlayer
 */
public class LocalPlayer implements Player {
    private MoveSrategy strategy;

    /**
     * Makes a local player
     * 
     * @param strategy the strategy to use for making a move
     */
    public LocalPlayer(MoveSrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void doMove() {
        Move move = strategy.getMove(null, null);

    }

    @Override
    public PlayerState getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }
}
