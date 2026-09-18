package games.kasia.app.games.tempgame;
import java.util.Scanner;
/**
 * The main method - entry point of the application.
 *
 * @param args command-line arguments
 */
public class TicTacToe {
    /**
     * dit print her bord
     */


    private char[][] gameBoard = {
                {'_', '|', '_', '|', '_'},
                {'_', '|', '_', '|', '_'},
                {' ', '|', ' ', '|', ' '}
        };

        private char currentPlayer = 'X';
    /**
     * Prints the game board.
     */

        public void printBoard(){
            for (char[] row : gameBoard) {
                for (char c : row) {
                    System.out.print(c);
                }
                System.out.println();
            }
        }

    /**
     * Starts the game.
     */
        public void playGame(){
            Scanner scanner = new Scanner(System.in);

            while(true){
                printBoard();

                System.out.println(
                        "Player " + currentPlayer
                        + ", choose a row (0-2) and column (0-2):"
                );

                int row = scanner.nextInt();
                int column = scanner.nextInt();

                gameBoard[row][column * 2] = currentPlayer;

                if (checkWinner()){
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                switchPlayer();

            }
        }
    /**
     * Switches between player X and player O.
     */
        private void switchPlayer(){
            if (currentPlayer == 'X'){
                currentPlayer = 'O';
            } else {
                currentPlayer = 'X';
            }
        }

    /**
     * checks if the current player has three in a row
     */
    private boolean checkWinner(){
        // check rows
        for (int row = 0; row < 3; row++) {
            if (gameBoard[row][0] == currentPlayer
            && gameBoard[row][2] == currentPlayer
            && gameBoard[row][4] == currentPlayer){
                return true;
            }
        }
        for (int column = 0; column < 5; column+= 2) {
            if (gameBoard[0][column] == currentPlayer
            && gameBoard[1][column] == currentPlayer
            && gameBoard[2][column] == currentPlayer){
                return true;
            }
        }

        if (gameBoard[0][0] == currentPlayer
        && gameBoard[1][2] == currentPlayer
        && gameBoard[2][4] == currentPlayer){
            return true;
        }
        if (gameBoard[0][4] == currentPlayer
        && gameBoard[1][2] == currentPlayer
        && gameBoard[2][0] == currentPlayer){
            return true;
        }
        return false;
    }
}




