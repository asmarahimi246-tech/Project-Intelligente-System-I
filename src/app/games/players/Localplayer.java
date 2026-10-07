package app.games.players;

import app.games.tictactoe.TicTacToeGame;

/**
 * LocalPlayer
 */
public class Localplayer {
    private TicTacToeGame game;
    private boolean aiMode;

    /**
     * Constructor
     */
    public Localplayer(TicTacToeGame game, boolean aiMode) {
        this.game = game;
        this.aiMode = aiMode; }

    /**
     * Play
     */
    public void play() {
        boolean gameRunning = true;
        while (gameRunning) {
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
