package app.games.tictactoe.players;

import java.util.ArrayList;
import java.util.function.Predicate;

import app.games.framework.Player;
import app.games.framework.PlayerState;

/**
 * RemotePlayer
 * 
 * This player waits for the remote
 * 
 * Its here to simulate an online opponent
 */
public class RemotePlayer implements Player {

    @Override
    public void doMove() {
        System.out.println("he;;p");
    }

    @Override
    public PlayerState getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

}
