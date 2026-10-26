package src.app.games.tictactoe.players;

import app.games.framework.game.Game;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerState;
import app.network.Client;

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
     * @return 
     */
    public OnlineOpponent(Client client) {
        this.client = client;

        // TODO: get the name from the parameter OPPONENT of the below server command
        // SVR GAME MATCH {PLAYERTOMOVE: "<naam speler1>", GAMETYPE: "<speltype>", OPPONENT: "<naam tegenstander>"}
        super.name = "";

        // TODO: get the name from the parameter PLAYERTOMOVE of the below server command
        // SVR GAME MATCH {PLAYERTOMOVE: "<naam speler1>", GAMETYPE: "<speltype>", OPPONENT: "<naam tegenstander>"}
        // and check if its the same as super.name to determine if this player does the first move
        isFirst = false;
        super.state = PlayerState.INITIALIZED;
    }

    @Override
    public void doMove(Game game) {
        super.state = PlayerState.CAN_MOVE;

        super.state = PlayerState.EXECUTED_TURN;
    }

    public boolean isFirst() {
        return isFirst;
    }

    /**
     * Waits for a turn
     */
    public void waitForTurn() {
        // CODE SMELL ):
        //
        // IDEA: maybe better to sort the svr messages based on message subtypes
        // by automaticaly making new queues for each subtype 
        // (^ feels like where entering a local minimum with this with the y axes being the shityness of the code)
        //
        // TODO: check if the player has moves get the move from the below command if parameter PLAYER is the same as super.name (probably needs a while loop)
        // SVR GAME MOVE {PLAYER: "<speler>", MOVE: "<zet>", DETAILS: "<reactie spel op zet>"}
        //
        // NOTE: there might be some problems with order
        // where the server first sends the below command:
        // SVR GAME YOURTURN {TURNMESSAGE: "<bericht voor deze beurt>"}
        // (please push it back to the head of the queue, online player needs it)
        // (^ the function for this is not implemented yet)
        super.state = PlayerState.CAN_MOVE;
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'waitForTurn'");
    }
}