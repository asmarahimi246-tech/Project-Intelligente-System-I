package app.games.tictactoe.players;

import app.games.framework.Player;
import app.games.framework.PlayerState;
import app.games.tictactoe.players.moveStrategies.Move;
import app.games.tictactoe.players.moveStrategies.MoveSrategy;

/**
 * OnlinePlayer
 */
public class OnlinePlayer implements Player {
    private MoveSrategy strategy;

    /**
     * Makes a online player
     * 
     * @param strategy the strategy to use for making a move
     */
    public OnlinePlayer(MoveSrategy strategy) {
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

    public void login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public void subscribe(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subscribe'");
    }

    public void waitForTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'waitForTurn'");
    }

    public boolean isFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFirst'");
    }
}
