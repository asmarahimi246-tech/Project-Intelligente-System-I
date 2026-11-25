package app.games.reversi.players;

import javax.naming.TimeLimitExceededException;

import app.games.framework.components.Board;
import app.games.framework.exceptions.TurnLimitExceededException;
import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerState;
import app.games.reversi.ReversiGame;
import app.games.reversi.model.ReversiMove;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.model.TicTacToeMove;
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
            ReversiGame reversi = (ReversiGame) game;

            Board board = reversi.model.getBoard();
            ReversiMove move = (ReversiMove) strategy.getMove(board, this);

            // send move
            if (move != null) {
                int serverMove = move.row()*8 +move.column();
                client.sendCommand("move " + serverMove);
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