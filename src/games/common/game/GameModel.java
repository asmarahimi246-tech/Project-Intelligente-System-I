package games.common.game;

public interface GameModel {

    boolean isValidMove(Move move);
    // check if move input is within board limits &
    // if tile isn't already occupied

    void placeMove(Move move);
    // place symbol on board

    boolean isGameOver();
    // a player won
    // board is full
    // a player quit

    boolean isBoardFull();

    GameResult getResult();
    // win/loss/draw

    String getPlayerSymbol();
    // X or O

    char[] getBoard();
}

//Store the board
//Keep track of the current player (X or O)
//Check if a move is valid
//Place a player's symbol on the board
//Check if someone has won
//Check if the game is a draw
//Keep track of if the game is over