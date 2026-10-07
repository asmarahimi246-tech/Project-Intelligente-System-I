package app.players;

import app.games.tictactoe.TicTacToeGame;

/**
 * <pre>
 * &gt; Implements {@code Player}.
 * &gt; Gets input from the (graphical) user interface (the terminal for now).
 * </pre>
 */
public class LocalPlayer {
    private TicTacToeGame game;
    private boolean aiMode;

    public LocalPlayer(TicTacToeGame game, boolean aiMode) {
        this.game = game;
        this.aiMode = aiMode; 
    }

    /**
     * TODO:
     */
    public void play() {
        while (true) {
            game.symbol('X');
            game.turn();

            if (game.checkWinner()) {
                System.out.println("Player X wins!");
                break;
            }

            if (game.isBoardFull()) {
                System.out.println("Draw!");
                break;
            }
            game.symbol('O');

            // laat de speler of de AI een move doen
            if (aiMode) {
                game.aiMove();
            } else {
                game.turn();
            }

            if (game.checkWinner()) {
                if (aiMode) {
                    System.out.println("AI wins!");
                } else {
                    System.out.println("Player O wins!");
                }
                break; }
            if (game.isBoardFull()) {
                System.out.println("Draw!");
                break;
            }
        }
    }
}