package app.players;
import java.util.Random;

/**
 * <pre>
 * &gt; Decides which move to make.
 * &gt; Implements {@code Player}.
 *
 * Example:
 *     &gt; getName() returns a name such as "PC", "COMP", or "BOT".
 *     &gt; pickMove():
 *         &gt; Checks the current board state.
 *         &gt; Chooses a move.
 *         &gt; Returns the chosen move.
 * </pre>
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
    public int numberAI(char[] board, char player) {

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
}