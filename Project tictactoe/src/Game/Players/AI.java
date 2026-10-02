package Game;

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

    }

    private boolean isBoardFull(char[] board) {
        return false;
    }

    private int checkMove(char[] move, char player) {

    }

    private int checkWin(char[] board, char player) {
        if () {

        }
        return 1;

    }

    private int checkLoss(char[] board, char player) {
        if () {

        }
        return -1;

    }

    private int checkDraw(char[] board, char player) {
        if () {

        }
        return 0;

    }
}
