package games.common.game;

public interface GameView {

    void displayBoard(char[] board);

    // player's name NOT their symbol
    void displayPlayerToMove(String playerName);

    // invalid move, winner/loser, draw, game over
    void displayMessage(String message);
}

//Display the game board
//Display whose turn it is
//Display messages (invalid move, winner, draw)