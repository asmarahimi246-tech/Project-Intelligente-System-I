package app.games.tictactoe.players;

import app.games.framework.Player;
import app.games.framework.PlayerState;
import app.games.tictactoe.players.moveStrategies.TicTacToeMove;
import app.games.tictactoe.players.moveStrategies.MoveSrategy;

/**
 * LocalPlayer
 */
public class LocalPlayer extends Player {
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
        super.state = PlayerState.CAN_MOVE;
        TicTacToeMove move = strategy.getMove(null, null);
        

        super.state = PlayerState.EXECUTED_TURN;
    }

}
