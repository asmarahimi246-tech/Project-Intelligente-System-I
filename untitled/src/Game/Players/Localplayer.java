package Game.Players;
import Game.Games.TicTacToe.TicTacToeGame;

public class Localplayer {
    private TicTacToeGame game;
    private boolean aiMode;

    public Localplayer(TicTacToeGame game, boolean aiMode) {
        this.game = game;
        this.aiMode = aiMode; }

    public void play() {
        while (true) {
            game.Symbol('X');
            game.Turn();

            if (game.checkWinner()) {
                System.out.println("Player X wins!");
                break;
            }

            if (game.isBoardFull()) {
                System.out.println("Draw!");
                break;
            }
            game.Symbol('O');

            // laat de speler of de AI een move doen
            if (aiMode) {
                game.aiMove();
            } else {
                game.Turn();
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
