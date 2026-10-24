package app.games.tictactoe.model;

/**
 * <pre>
 * &gt; tictactoe rules &amp; game state
 *     &gt; board
 *     &gt; whose turn it is (X/O)
 *     &gt; if move is legal
 *     &gt; how moves change the board
 *     &gt; when win/loss/draw happens
 *     &gt; who won (X/O)
 *
 * &gt; must work separate from
 *     &gt; how board is displayed
 *     &gt; where input came from (terminal/gui/server)
 *     &gt; what type of player made the move (ai/human)
 *
 * EX:
 * &gt; doMove() --&gt; send move to Controller so it can tell View to update with the move made
 * &gt; bool: isValidMove()
 * &gt; SwitchTurn() --&gt; change per move whose turn it is
 * &gt; bool: isGameOVer()
 * &gt; GameResult: getResult()
 * </pre>
 */
public class TicTacToeModel {
    
}
