package app.games.players;

import java.util.Random;

/**
 * AI
 */
public class AI {

    /**
     *
     * @param board Board to play on
     * @param max Maximising or minimising player
     * @param player
     * @return
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

        char opponent;

        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        if (max) {
            int maxscore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char a = board[i];
                    board[i] = player;

                    int score = minimax(board, !max, opponent);
                    board[i] = a;
                    maxscore = Math.max(maxscore, score);
                }

            }
            return maxscore;

        } else {
            int minscore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char a = board[i];
                    board[i] = player;

                    int score = minimax(board, !max, opponent);
                    board[i] = a;
                    minscore = Math.min(minscore, score);
                }

            }
            return minscore;

        }

    }

    public int getBestMove(char[] board, char player) {

        // zoekt eerst naar een winnende zet voor de AI
        for (int i = 0; i < 9; i++) {
            if (checkMove(board, player, i)) {
                return i + 1;
            }
        }

        char opponent;

        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        // probeert een winnende zet van de tegenstander te blokkeren
        for (int i = 0; i < 9; i++) {
            if (checkMove(board, opponent, i)) {
                return i + 1;
            }
        }

        // als er geen directe winnende zet of block is kiest de AI een random positie
        Random random = new Random();

        while (true) {
            int randomMove = random.nextInt(9);

            // controlleert of een pos vrij is
            if (board[randomMove] != 'X' && board[randomMove] != 'O') {
                return randomMove + 1;
            }
        }
    }

    /**
     *
     * @param board
     * @return
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
     * controlleert of een pos gebruikt kan worden voor een winnende zet
     *
     * @param board
     * @param player
     * @param pos
     * @return
     */
    private boolean checkMove(char[] board, char player, int pos) {

        // een bezette pos mag niet
        if (board[pos] == 'X' || board[pos] == 'O') {
            return false;
        }

        char a = board[pos];
        board[pos] = player;

        // checkt of deze zet een winning move is
        if (checkWinningMove(board, player)) {
            board[pos] = a;
            return true;
        }
        board[pos] = a;

        return false;
    }

    /**
     *
     * @param board
     * @param player
     * @return
     */
    private int checkWin(char[] board, char player) {
        if (checkWinningMove(board, player)) {
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param board
     * @param player
     * @return
     */
    private int checkLoss(char[] board, char player) {
        char opponent;

        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        if (checkWinningMove(board, opponent)) {
            return -1;
        }
        return 0;
    }

    /**
     *
     * @param board
     * @param player
     * @return
     */
    private boolean checkDraw(char[] board, char player) {
        return isBoardFull(board);
    }

    /**
     * TODO: hoort hier niet te staan
     * @param board
     * @param winningMove
     * @return
     */
    private boolean checkWinningMove(char[] board, char winningMove) {
        if (board[0] == winningMove && board[1] == winningMove && board[2] == winningMove) {
            return true;
        }
        if (board[3] == winningMove && board[4] == winningMove && board[5] == winningMove) {
            return true;
        }
        if (board[6] == winningMove && board[7] == winningMove && board[8] == winningMove) {
            return true;
        }
        if (board[0] == winningMove && board[3] == winningMove && board[6] == winningMove) {
            return true;
        }
        if (board[1] == winningMove && board[4] == winningMove && board[7] == winningMove) {
            return true;
        }
        if (board[2] == winningMove && board[5] == winningMove && board[8] == winningMove) {
            return true;
        }
        if (board[0] == winningMove && board[4] == winningMove && board[8] == winningMove) {
            return true;
        }
        if (board[2] == winningMove && board[4] == winningMove && board[6] == winningMove) {
            return true;
        }
        return false;
    }
}
