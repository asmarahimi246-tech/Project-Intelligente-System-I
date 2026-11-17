package games.tictactoe;

import games.common.game.GameView;

public class TicTacToeView implements GameView {

    @Override
    public void displayBoard(char[] board) {
        IO.println("""
        | %s | %s | %s |
        +---+---+---+
        | %s | %s | %s |
        +---+---+---+
        | %s | %s | %s |
        """.formatted(
                        board[0], board[1], board[2],
                        board[3], board[4], board[5],
                        board[6], board[7], board[8]
                )
        );
    }

    @Override
    public void displayPlayerToMove(String playerName) {
        System.out.println("Player to move: " + playerName);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

}