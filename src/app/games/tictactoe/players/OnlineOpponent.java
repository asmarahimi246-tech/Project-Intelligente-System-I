package app.games.tictactoe.players;

import javax.naming.TimeLimitExceededException;

import app.games.framework.exceptions.MatchNotFoundException;
import app.games.framework.exceptions.TurnLimitExceededException;
import app.games.framework.game.Game;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerState;
import app.games.tictactoe.model.TicTacToeMove;
import app.network.Client;
import app.network.MessageAddon;
import app.network.MessageSubtype;
import app.network.ServerMessage;
import app.util.Config;
import app.util.Logger;

/**
 * OnlineOpponent
 * 
 * This player represents the opponent in an online game
 * 
 * Its here to read what the opponent does from the server
 */
public class OnlineOpponent extends Player {
    private Client client; // the client is shared with players
    private boolean isFirst;

    /**
     * Makes a remote player
     * 
     * @param client the server connection to use
     */
    public OnlineOpponent(Client client) {
        this.client = client;
    }

    /**
     * Logs into the server
     */
    public void waitForMatch() {
        // wait for match
        ServerMessage msg;
        try {
            msg = client.getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype.GAME, MessageAddon.MATCH, Integer.MAX_VALUE);
            Logger.logDebug(msg.toString());

            // get the opponent name
            super.name = msg.getGameDetails().get("OPPONENT");
            Logger.logDebug("opponent = " + name);

            // check if the opponent is first
            String toMove = msg.getGameDetails().get("PLAYERTOMOVE");
            Logger.logDebug("player to move = " + toMove);
            if (toMove.equalsIgnoreCase(super.name)) {
                Logger.logDebug("opponent starts");
                isFirst = true;
            } else {
                Logger.logDebug("player starts");
                isFirst = false;
            }
        } catch (TimeLimitExceededException e) {

            //TODO: no thread interupt here
            Logger.logErr("No match found");
            throw new MatchNotFoundException();
        }

        super.state = PlayerState.INITIALIZED;
    }

    @Override
    public void doMove(Game game) {
        Logger.logDebug("opponent move");
        Logger.logDebug(name);

        // get the move
        ServerMessage msg;
        try {
            msg = client.getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype.GAME, MessageAddon.MOVE, Config.TURNLIMIT);

            while (!msg.getGameDetails().get("PLAYER").equalsIgnoreCase(super.name)) {
                Logger.logDebug("key = " + msg.getGameDetails().get("PLAYER"));

                msg = client.getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype.GAME, MessageAddon.MOVE, Config.TURNLIMIT);
            }

            Logger.logDebug(msg.toString());
            int serverMove = Integer.parseInt(msg.getGameDetails().get("MOVE"));

            TicTacToeMove move = new TicTacToeMove(serverMove/3, serverMove%3);

            // process rge move
            game.procesMove(move);
        } catch (TimeLimitExceededException e) {
            Logger.logDebug("Opponent turnlimit exceeded");
            throw new TurnLimitExceededException();
        }
        super.state = PlayerState.EXECUTED_TURN;
    }

    /**
     * Checks if this player is first to move
     * 
     * @return true if first to move
     */
    public boolean isFirst() {
        return isFirst;
    }
}