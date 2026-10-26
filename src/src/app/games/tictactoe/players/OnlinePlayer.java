package src.app.games.tictactoe.players;

import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerState;
import app.games.tictactoe.model.TicTacToeMove;
import app.network.Client;
import app.network.ServerMessage;
import app.util.NameGenerator;

import java.util.ArrayList;

/**
 * OnlinePlayer
 */
public class OnlinePlayer extends Player {
    private MoveSrategy strategy;
    private Client client; // the client is always shared with the online opponent

    /**
     * Makes an online player
     * 
     * @param strategy the strategy to use for making a move
     */
    public OnlinePlayer(MoveSrategy strategy, Client client) {
        super();
        this.strategy = strategy;
        this.client = client;
        super.state = PlayerState.INITIALIZED;

    }

    public void initialize() {
        if (client == null) {
            throw new IllegalStateException(
                    "Cannot initialize OnlinePlayer without a Client"
            );
        }

        if (getState() == PlayerState.INITIALIZED) {
            login();
        }

        if (getState() == PlayerState.LOGGED_IN) {
            subscribe();
        }

        if (getState() == PlayerState.SUBSCRIBED) {
            waitForTurn();
        }
    }

    @Override
    public void doMove(Game game) {
        while (super.state != PlayerState.CAN_MOVE) {
            waitForTurn();
        }
        TicTacToeMove move = strategy.getMove(game);
        game.procesMove(move);
        super.state = PlayerState.EXECUTED_TURN;
    }


    private String generateUniqueName() {
        client.sendCommand("get playerlist");
        ServerMessage msg = client.getNextSvrMessage();
        ArrayList<String> playerlist = msg.getListDetails();

        NameGenerator nameGenerator = new NameGenerator();
        String name = nameGenerator.getName();

        // check if the random name is taken
        // if so, generate a new one
        // if not the name is unique and can be returned
        while (playerlist.contains(name)) {
            name = nameGenerator.getName();
        }
        return name;
    }

    public void login() {
        String name = generateUniqueName();
        client.sendCommand("login " + name);

//        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public void subscribe() {
        client.sendCommand("subscribe tic-tac-toe" );
//        throw new UnsupportedOperationException("Unimplemented method 'subscribe'");
    }

    /**
     * Waits for a turn
     */
    public void waitForTurn() {
        super.state = PlayerState.CAN_MOVE;
        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'waitForTurn'");
    }
}