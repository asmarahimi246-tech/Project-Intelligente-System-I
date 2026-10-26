package src.app.games.tictactoe.model;

import app.games.framework.move.Move;

/**
 * Move record for tic tac toe
 * 
 * Having a record implement a interface feels wrong
 * 
 * Tells where a tile is going to be placed
 * Easy way to transport data
 * 
 * @param row of the tile
 * @param column of the tile
 */
public record TicTacToeMove(int row, int column) implements Move {}