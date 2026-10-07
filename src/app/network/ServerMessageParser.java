package app.network;

/**
 * <pre>
 * &gt; Parses server messages into their component parts.
 * &gt; This functionality may be split across several classes, but does not
 *   need to be.
 *
 * Example:
 * Input:
 * SVR GAME MATCH {PLAYERTOMOVE: "player1", GAMETYPE: "tic-tac-toe",
 *                 OPPONENT: "opponent"}
 *
 * Output: a ServerMessage object with:
 *     &gt; type = SVR
 *     &gt; subtype = MATCH
 *     &gt; fields:
 *         &gt; playerToMove = player name
 *         &gt; game = game name
 *         &gt; opponent = opponent name
 *
 * Example:
 * Input:
 * SVR GAME WIN/LOSS/DRAW {PLAYERONESCORE: "score1",
 *                         PLAYERTWOSCORE: "score2",
 *                         COMMENT: "result comment"}
 *
 * Output: a ServerMessage object with:
 *     &gt; type = SVR
 *     &gt; subtype = GAME_RESULT
 *     &gt; fields:
 *         &gt; player1Score = player 1 score
 *         &gt; player2Score = player 2 score
 *         &gt; comment/message = result comment (e.g., "You won!")
 *
 * The view is responsible for displaying or otherwise handling the
 * comment/message.
 * </pre>
 */
public class ServerMessageParser {
}
