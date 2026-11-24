package app.games.tictactoe.players;

import app.games.framework.components.Board;
import app.games.framework.game.Game;
import app.games.framework.move.MoveSrategy;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerState;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.model.TicTacToeMove;

/**
 * LocalPlayer
 */
public class LocalPlayer extends Player {


    /**
     * Makes a local player
     * 
     * @param strategy the strategy to use for making a move
     */
    public LocalPlayer(MoveSrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void doMove(Game game) {
        super.state = PlayerState.CAN_MOVE;
        TicTacToeGame ticTacToe = (TicTacToeGame) game;

        Board board = ticTacToe.model.getBoard();

        TicTacToeMove move = (TicTacToeMove) strategy.getMove(board, this);
        game.procesMove(move);
        super.state = PlayerState.EXECUTED_TURN;
    }
}
