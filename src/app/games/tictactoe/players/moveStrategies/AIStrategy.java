package app.games.tictactoe.players.moveStrategies;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

/**
 * AI
 */
public class AIStrategy implements MoveSrategy {
    /**
     * Temp
     * 
     * @param board board
     * @param max max
     * @param player player
     * @return iets
     */
    private int minimax(char[] board, boolean max, char player) {
        if (checkWin(board, player) == 1) {
            return 1;
        }

        if (checkLoss(board, player) == -1) {
            return -1;
        }

        if (checkDraw(board, player)) {
            return 0;
        }

        char simulatedopponent;

        if (player == 'X') {
            simulatedopponent = 'O';
        } else {
            simulatedopponent = 'X';
        }

        if (max) {
            int bestscore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char a = board[i];
                    board[i] = player;

                    int score = minimax(board, !max, simulatedopponent);
                    board[i] = a;
                    bestscore = Math.max(bestscore, score);
                }

            }
            return bestscore;

        } else {
            int minscore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char a = board[i];
                    board[i] = player;

                    int score = minimax(board, !max, simulatedopponent);
                    board[i] = a;
                    minscore = Math.min(minscore, score);
                }
            }
            return minscore;
        }
    }

    /**
     * Temp
     * 
     * @param board board
     * @param player player
     * @return iets
     */
    public TicTacToeMove numberAI(ArrayList<String> board, char player) {

        // zoekt eerst naar een winnende zet voor de AI
        for (int i = 0; i < 9; i++) {
            //if (checkMove(board, player, i)) {
            //    return i + 1;
            //}
        }

        char opponent;

        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        // probeert een winnende zet van de tegenstander te blokkeren
        for (int i = 0; i < 9; i++) {
            //if (checkMove(board, opponent, i)) {
            //    return i + 1;
            //}
        }

        // als er geen directe winnende zet of block is kiest de AI een random positie
        Random random = new Random();

        while (true) {
            int randomMove = random.nextInt(9);

            // controlleert of een pos vrij is
            //if (board[randomMove] != 'X' && board[randomMove] != 'O') {
            //    return randomMove + 1;
            //}
        }
    }

    /**
     * Temp
     * @param board board
     * @param player player
     * @param pos pos 
     * @return iets
     */
    private boolean checkMove(ArrayList<String> board, char player, int pos) {

        // een bezette pos mag niet
        //if (board[pos] == 'X' || board[pos] == 'O') {
        //    return false;
        //}

        //char a = board[pos];
        //board[pos] = player;

        // checkt of deze zet een winning move is
        //if (minimax(board, player)) {
        //    board[pos] = a;
        //    return true;
        //}
        //board[pos] = a;

        return false;
    }

    /**
     * Temp
     * @param board board
     * @return iets
     */
    private boolean isBoardFull(char[] board) {
        for (char vak : board) {
            if (vak != 'X' && vak != 'O') {
                return false;
            }
        }
        return true;
    }

    /**
     * Temp
     * @param board board
     * @param player player
     * @return iets
     */
    private int checkWin(char[] board, char player) {
        //if (minimax(board, player)) {
        //    return 1;
        //}
        return 0;
    }

    /**
     * Temp
     * @param board board
     * @param player player
     * @return iets
     */
    private int checkLoss(char[] board, char player) {
        char opponent;

        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        //if (minimax(board, opponent)) {
        //    return -1;
        //}
        return 0;
    }

    /**
     * Temp
     * @param board board
     * @param player player
     * @return iets
     */
    private boolean checkDraw(char[] board, char player) {
        return isBoardFull(board);
    }

    @Override
    public TicTacToeMove getMove(ArrayList<String> board, Predicate<TicTacToeMove> validateMove) {
        return numberAI(board, 'X');
    }
}
