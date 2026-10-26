package src.app.network;

import app.network.MessageAddon;
import app.network.MessageSubtype;
import app.network.MessageType;

import java.util.ArrayList;
import java.util.Map;

public class ServerMessage {
    private final app.network.MessageType type;
    private app.network.MessageSubtype subtype = null;
    private app.network.MessageAddon addon = null;
    private Map<String, String> gameDetails = null;
    private String errorDetails = null;
    private ArrayList<String> listDetails = null;
    private String helpDetails = null;

    /**
     * Constructor for "OK" server messages
     * @param type type of server message --> OK
     */
    public ServerMessage(app.network.MessageType type) {
        this.type = type;
    }

    /**
     * Constructor for "ERR" server messages
     * @param type type of server message --> ERR
     * @param errorDetails further details of server message
     */
    public ServerMessage(app.network.MessageType type, String errorDetails) {
        this.type = type;
        this.errorDetails = errorDetails;
    }

    /**
     * Constructor for "SVR HELP" server messages
     * @param type type of server message --> SVR
     * @param subtype subtype of server message --> HELP
     * @param helpDetails further details of server message
     */
    public ServerMessage(app.network.MessageType type,
                         app.network.MessageSubtype subtype,
                         String helpDetails) {
        this.type = type;
        this.subtype = subtype;
        this.helpDetails = helpDetails;
    }

    /**
     * Constructor for "SVR GAME" server messages
     * @param type type of server message --> SVR
     * @param subtype subtype of server message --> GAME
     * @param addon addon to subtype of server message
     *              --> WIN/LOSS/DRAW/MATCH
     *              --> YOURTURN/MOVE
     *              --> CHALLENGE/CANCELLED
     * @param gameDetails further details of server message
     */
    public ServerMessage(app.network.MessageType type, app.network.MessageSubtype subtype,
                         app.network.MessageAddon addon, Map<String, String> gameDetails) {
        this.type = type;
        this.subtype = subtype;
        this.addon = addon;
        this.gameDetails = Map.copyOf(gameDetails);
    }

    /**
     * Constructor for "SVR GAMELIST/PLAYERLIST"
     * @param type type of server message --> SVR
     * @param subtype subtype of server message --> GAMELIST/PLAYERLIST
     */
    public ServerMessage(app.network.MessageType type,
                         app.network.MessageSubtype subtype,
                         ArrayList<String> listDetails) {
        this.type = type;
        this.subtype = subtype;
        this.listDetails = listDetails;
    }

    public MessageType getType() {
        return type;
    }

    public MessageSubtype getSubtype() {
        return subtype;
    }

    public MessageAddon getAddon() {
        return addon;
    }

    public String getDetailValue(String key) {
        return gameDetails.get(key);
    }

    public Map<String, String> getGameDetails() {
        return gameDetails;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public ArrayList<String> getListDetails() {
        return listDetails;
    }

    public String getHelpDetails() {
        return helpDetails;
    }
}