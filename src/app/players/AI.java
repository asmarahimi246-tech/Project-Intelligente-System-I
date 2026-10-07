package app.players;
import java.util.Random;

/**
 * > decides what move to make
 * > implements Player
 * 
 * EX:
 * > getName() = "PC" "COMP" "BOT" or smth
 * > pickMove()
 *     > check current state of board
 *     > make decision
 *     > return chosen move
 */
public class AI {
    // TODO: do iets met de interface player 
    // public class AI implements player < dit moet je doen om de interface te gebruiken

    /** TODO: 
     * toekomst eerst bezig met random AI/eigen AI die soortvan beste move pakt.
     * 
     * waarom is dit public
     * de game hoeft niet te weten dat de AI deze functie
     * daarvoor is een abstractie laag genaamd de interfece player
     * die heeft de functie die de game roept
    */
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

    /**
     * TODO: wat doet dit hier
     * dit hoort in de game model
     * de game model heeft de regels van de game 
     * de ai niet
     * de ai leest van de game
     * 
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

    /**
     * TODO: het zelfdegeld voor deze functie
     * 
     * @param board
     * @param player
     * @param pos
     * @return
     */
    private boolean checkMove(char[] board, char player, int pos) {
        if (board[pos] == 'X' || board[pos] == 'O') {
            return false;
        }

        char a = board[pos];
        board[pos] = player;

        if (checkWinningMove(board, player)) {
            board[pos] = a;
            return true;
        }
        board[pos] = a;

        return false;
    }

//    private int checkWin(char[] board, char player) {
//
//
//
//        return 1;
//
//    }
//
//    private int checkLoss(char[] board, char player) {
//
//
//
//        return -1;
//
//    }
//
//    private int checkDraw(char[] board, char player) {
//
//
//
//        return 0;
//
//    }
}