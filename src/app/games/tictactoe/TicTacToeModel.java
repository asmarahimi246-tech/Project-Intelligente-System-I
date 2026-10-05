package app.games.tictactoe;

/**
 * > tictactoe rules & game state
 *     > board
 *     > whose turn it is (X/O)
 *     > if move is legal
 *     > how moves change the board
 *     > when win/loss/draw happens
 *     > who won (X/O)
 * 
 * > must work separate from
 *     > how board is displayed
 *     > where input came from (terminal/gui/server)
 *     > what type of player made the move (ai/human)
 * 
 * EX:
 * > doMove() --> send move to Controller so it can tell View to update with the move made
 * > bool: isValidMove()
 * > SwitchTurn() --> change per move whose turn it is
 * > bool: isGameOVer()
 * > GameResult: getResult()
 */
public class TicTacToeModel {
}
