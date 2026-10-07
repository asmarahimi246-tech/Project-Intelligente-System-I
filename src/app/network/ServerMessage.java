package app.network;

/**
 * <pre>
 * &gt; Creates objects from parsed server messages.
 *
 * Example:
 * &gt; enum MessageType
 *     &gt; OK
 *     &gt; ERR
 *     &gt; SRV
 * &gt; enum MessageSubtype
 *     &gt; GAMELIST
 *     &gt; PLAYERLIST
 *     &gt; HELP
 *     &gt; MATCH
 *     &gt; YOURTURN
 *     &gt; MOVE
 *     &gt; WIN_LOSS_DRAW
 *     &gt; CHALLENGE
 * &gt; Map&lt;String, String&gt; fields // Remaining message data
 * &gt; Getters
 *
 * Example message:
 * SVR GAME MATCH {PLAYERTOMOVE: "player1", GAMETYPE: "tic-tac-toe",
 *                 OPPONENT: "opponent"}
 *
 *     &gt; MessageType = SRV
 *     &gt; MessageSubtype = MATCH
 *     &gt; fields =
 *         PLAYERTOMOVE: "player1"
 *         GAMETYPE: "tic-tac-toe"
 *         OPPONENT: "opponent"
 *
 * To simplify field access, provide getters that look up the relevant key:
 *     &gt; getOpponent() --> returns fields.get("OPPONENT") // "opponent"
 * </pre>
 */
public class ServerMessage {
}