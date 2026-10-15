package app.games.tictactoe.players.moveStrategies;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

import app.games.tictactoe.TicTacToePage;
import app.util.ScannerSingleton;
import app.util.cliInputFields.CLIIntegerField;

/**
 * UserStrategy
 * 
 * A strategy to do a move by geting user input
 * Uses the page to get input from
 * 
 * TODO: input from page (send message to Yuna if this is not possible)
 */
public class UserStrategy implements MoveSrategy {
    TicTacToePage page;

    /**
     * Makes a user strategy
     * 
     * @param page for gui input
     */
    public UserStrategy(TicTacToePage page) {
        this.page = page;
    }

    @Override
    public Move getMove(ArrayList<String> board, Predicate<Move> validateMove) {
        Move toReturn = null;

        // print
        System.out.println(board);

        // get input
        while (!validateMove.test(toReturn)){

            System.out.println("give row");
            Scanner scanner = ScannerSingleton.getInstance();
            CLIIntegerField xfield = new CLIIntegerField.Builder(scanner, "Something went wrong.")
                .max(3)
                .min(1)
                .build();
            int row = xfield.getInput();

            System.out.println("now collom");
            CLIIntegerField yfield = new CLIIntegerField.Builder(scanner, "Something went wrong.")
                .max(3)
                .min(1)
                .build();
            int collomn = yfield.getInput();

            toReturn = new Move(row, collomn);
        }

        return toReturn;
    }
}
