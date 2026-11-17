package games.tictactoe;

import games.common.game.GameModel;
import games.common.game.GameResult;
import games.common.game.Move;

public class TicTacToeModel implements GameModel {
    private char[] board;
    private char currentSymbol; // X or O
    private GameResult result; // win/loss/draw
    private boolean gameOver;

    public TicTacToeModel() {
        this.board = new char[] {'1', '2', '3',
                                 '4', '5', '6',
                                 '7', '8', '9'};
        this.currentSymbol = 'X';
        this.gameOver = false;
    }

    @Override
    public boolean isValidMove(Move move) {
        return false;
    }

    @Override
    public void placeMove(Move move) {
        // place symbol on board
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean isBoardFull() {
        return false;
    }

    @Override
    public GameResult getResult() {
        return null;
    }

    @Override
    public String getPlayerSymbol() {
        return null;
    }

    @Override
    public char[] getBoard() {
        return board;
    }
}