package app.network;

import java.util.HashMap;

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
        private HashMap<String, String> params = new HashMap<String, String>();

    /**
     * The WHYson decoder alows you to get the values of each param
     * 
     * @param whyson the WHYson to decode format of a WHYson:
     * COMMAND {PARAM: "value"}
     */
    public ServerMessageParser(String whyson) {
        setup(whyson);
    }

    /**
     * decodes the WHYson
     * 
     * @param whyson
     */
    private void setup (String whyson) {
        whyson = whyson.substring(whyson.indexOf("{") + 1);
        String whysonParamsString = whyson.substring(0, whyson.indexOf("}"));

        String[] whysonParams = whysonParamsString.split(",");

        for (String param : whysonParams) {
            String[] paramTuple = param.split(":");

            String value = paramTuple[1].substring(paramTuple[1].indexOf("\"") + 1);
            String cleanValue = value.substring(0, value.indexOf("\"")).trim();

            params.put(paramTuple[0].trim(), cleanValue);
        }
    }

    /**
     * gets the value of a param
     * 
     * @param param
     * @return
     */
    public String getValue (String param) {
        return params.get(param);
    }
}
