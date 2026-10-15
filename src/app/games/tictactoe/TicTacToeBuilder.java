package app.games.tictactoe;

import app.games.framework.GameBuilder;

/**
 * builder
 * builds the game
 */
public class TicTacToeBuilder extends GameBuilder<TicTacToeGame,TicTacToeBuilder> {
    @Override
    public String toString() {
        return "Tic Tac Toe Builder";
    }

    @Override
    protected TicTacToeBuilder self() {
        return this;
    }

    @Override
    public TicTacToeGame build() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'build'");
    }

    @Override
    public void printOptions() {
        if (super.mode == "online") {
            System.out.println("(1) onlineBot");
            System.out.println("(2) onlinePlayer");

        } else if (super.mode == "local") {
            System.out.println("(1) localBot");
            System.out.println("(2) localPlayer");
        }
    }

}
