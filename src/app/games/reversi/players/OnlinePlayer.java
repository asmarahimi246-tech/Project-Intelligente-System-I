package app.games.reversi.players;

import javax.naming.TimeLimitExceededException;

import app.games.framework.exceptions.TurnLimitExceededException;
import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerState;
import app.games.reversi.model.ReversiMove;
import app.network.Client;
import app.network.MessageAddon;
import app.network.MessageSubtype;
import app.util.Config;
import app.util.Logger;

/**
 * OnlinePlayer
 */
public class OnlinePlayer extends Player {
    private Client client; // the client is always shared with the remote

    /**
     * Makes an online player
     * 
     * @param strategy the strategy to use for making a move
     * @param client the client it uses to connect to the server
     */
    public OnlinePlayer(MoveSrategy strategy, Client client) {
        super();
        this.strategy = strategy;
        this.client = client;
        super.state = PlayerState.INITIALIZED;
    }

    @Override
    public void doMove(Game game) {
        // wait for a move
        try {
            client.getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype.GAME, MessageAddon.YOURTURN, Config.TURNLIMIT);

            // get the move
            //TODO: scan terminal here
            ReversiMove move = null;

            // send move
            if (move != null) {
                //TODO: go from move to server move
                //int serverMove =
                //client.sendCommand("move " + serverMove);
            }

            // process the move
            game.procesMove(move);
            super.state = PlayerState.EXECUTED_TURN;
        } catch (TimeLimitExceededException e) {
            Logger.logErr("Turnlimit exceeded. Connection with server lost?");
            throw new TurnLimitExceededException();
        }
    }
}