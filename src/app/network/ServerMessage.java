package app.network;

/**
 * > create objects based on parsed server message
 * 
 * EX:
 *     > enum MessageType
 *         > OK
 *         > ERR
 *         > SRV
 *     > enum MessageSubtype
 *         > GAMELIST
 *         > PLAYERLIST
 *         > HELP
 *         > MATCH
 *         > YOURTURN
 *         > MOVE
 *         > WIN_LOSS_DRAW
 *         > CHALLENGE
 *     > Map<String, String> fields // rest of server message info
 *     + getters
 * 
 *     EX:
 *     SVR GAME MATCH {PLAYERTOMOVE: "<naam speler1>", GAMTYPE: "<speltype>", OPPONENT: "<naam tegenstander>"}
 *         > MessageType = SVR
 *         > MessageSubtype = MATCH
 *         > fields =
 *             PLAYERTOMOVE: "bob",
 *             GAMETYPE: "tic-tac-toe",
 *             OPPONENT: "henk"
 *         > to make fields handling easier put keyword in getter call
 *             --> getOpponent() --> return fields.get("OPPONENT") // "henk"
 */
public class ServerMessage {
}