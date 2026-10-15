package app.games.tictactoe.players.moveStrategies;

/**
 * Move record for tic tac toe
 * 
 * Tells where a tile is going to be placed
 * Easy way to transport data
 * 
 * @param row of the tile
 * @param column of the tile
 */
public record Move(int row, int column) {}