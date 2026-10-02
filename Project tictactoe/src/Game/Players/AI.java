package Game.Players;
import java.util.Random;

public class AI {

    // toekomst eerst bezig met random AI/eigen AI die soortvan beste move pakt.
    public int numberAI(char[] move, char player) {

        for (int i = 0; i < 9; i++) {
            if (checkMove(move, player, i)) {
                return i + 1;
            }
        }

        char opponent;

        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        for (int i = 0; i < 9; i++) {
            if (checkMove(move, opponent, i)) {
                return i + 1;
            }
        }
        Random random = new Random();

        while (true) {
            int randomMove = random.nextInt(9);

            if (move[randomMove] != 'X' && move[randomMove] != 'O') {
                return randomMove + 1;
            }
        }
    }

//    private boolean isBoardFull(char[] board) {
//        for (char vak: board) {
//            if (vak != 'X' && vak !='O') {
//                return false;
//            }
//        }
//        return true;
//    }

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

    private boolean checkMove(char[] board, char player, int pos) {
        if (board[pos] == 'X' || board[pos] == 'O') {
            return false;
        }

        char a = board[pos];
        board[pos] = player;

    }

    private int checkDraw(char[] board, char player) {
        if () {

        }
        return 0;

    }
}
