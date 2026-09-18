package games.kasia.app;

import games.kasia.app.games.tempgame.TicTacToe;

/**
 * 
 * App
 * 
 * yes
 */
public class App {
    /**
     * The main method - entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main( String[] args )
    {
        TicTacToe game = new TicTacToe();
        game.playGame();
//        game.test();
//        int kaas = 11;

    }

//    private void test()
//    {
//        System.out.println("kaas");
//    }
}