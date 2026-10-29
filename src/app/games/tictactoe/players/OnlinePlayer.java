package app.games.tictactoe.players;

import java.util.ArrayList;

import app.games.tictactoe.model.TicTacToeMove;
import app.games.framework.game.Game;
import app.games.framework.game.GameType;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerState;
import app.network.Client;
import app.network.MessageAddon;
import app.network.MessageSubtype;
import app.network.ServerMessage;
import app.util.Logger;
import app.util.NameGenerator;

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
        client.getSvrMessageBySubTypeAndOptionalAddon(MessageSubtype.GAME, MessageAddon.YOURTURN);

        // get the move
        TicTacToeMove move = strategy.getMove(game);

        // send move
        if (move != null) {
            int serverMove = move.row()*3 + move.column();
            client.sendCommand("move " + serverMove);
        }

        // process rge move
        game.procesMove(move);
        super.state = PlayerState.EXECUTED_TURN;
    }

    /**
     * Subscribes to the game
     */
    public void subscribe() {
        client.sendCommand("subscribe tic-tac-toe" );
    }
}