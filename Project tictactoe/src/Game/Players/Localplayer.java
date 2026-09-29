package Game.Players;
import Game.Games.TicTacToe.TicTacToeGame;

public class Localplayer {
    private TicTacToeGame game;

    public Localplayer(TicTacToeGame game) {
        this.game = game;
    }

    public void play() {
        while(true){
            game.Symbol('x');
            game.Turn();

            game.Symbol('o');
            game.Turn();
        }

    }

}
