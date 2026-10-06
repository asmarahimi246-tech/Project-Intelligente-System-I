package app.Game.Players;
import java.util.Random;

public class AI {

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

    // controlleert of een pos gebruikt kan worden voor een winnende zet
    private boolean checkMove(char[] board, char player, int pos) {

        // een bezette pos mag niet
        if (board[pos] == 'X' || board[pos] == 'O') {
            return false;
        }

        char a = board[pos];
        board[pos] = player;

        // checkt of deze zet een winning move is
        if (isGameOver(board, player)) {
            board[pos] = a;
            return true;
        }
        board[pos] = a;

        return false;
    }
}

/*
> decides what move to make
> implements Player

EX:
> getName() = "PC" "COMP" "BOT" or smth
> pickMove()
    > check current state of board
    > make decision
    > return chosen move
*/