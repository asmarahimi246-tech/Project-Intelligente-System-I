package app.games.reversi.players;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.reversi.players.moveStrategies.UserStrategy;

/**
 * LocalPlayer
 */
public class LocalPlayer extends Player {

    public LocalPlayer(MoveSrategy strategy) {
        this.strategy = strategy;
    }
    @Override
    public void doMove(Game game) {

    }
}
