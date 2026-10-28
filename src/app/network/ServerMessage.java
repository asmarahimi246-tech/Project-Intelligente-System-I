package app.network;

import java.util.ArrayList;
import java.util.Map;

/**
 * ServerMessage
 */
public class ServerMessage {
    private final MessageType type;
    private MessageSubtype subtype = null;
    private MessageAddon addon = null;
    private Map<String, String> gameDetails = null;
    private String errorDetails = null;
    private ArrayList<String> listDetails = null;
    private String helpDetails = null;

    @Override
    public String toString() {
        return "ServerMessage{" +
                "type=" + type +
                (subtype != null ? ", subtype=" + subtype : "") +
                (addon != null ? ", addon=" + addon : "") +
                (gameDetails != null ? ", gameDetails=" + gameDetails : "") +
                (errorDetails != null ? ", errorDetails='" + errorDetails + '\'' : "") +
                (listDetails != null ? ", listDetails=" + listDetails : "") +
                (helpDetails != null ? ", helpDetails='" + helpDetails + '\'' : "") +
                '}';
    }

    /**
     * Constructor for "OK" server messages
     * @param type type of server message --> OK
     */
    public ServerMessage(MessageType type) {
        this.type = type;
    }

    /**
     * Constructor for "ERR" server messages
     * @param type type of server message --> ERR
     * @param errorDetails further details of server message
     */
    public ServerMessage(MessageType type, String errorDetails) {
        this.type = type;
        this.errorDetails = errorDetails;
    }

    /**
     * Constructor for "SVR HELP" server messages
     * @param type type of server message --> SVR
     * @param subtype subtype of server message --> HELP
     * @param helpDetails further details of server message
     */
    public ServerMessage(MessageType type,
                         MessageSubtype subtype,
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
    public ServerMessage(MessageType type, MessageSubtype subtype,
                         MessageAddon addon, Map<String, String> gameDetails) {
        this.type = type;
        this.subtype = subtype;
        this.addon = addon;
        this.gameDetails = Map.copyOf(gameDetails);
    }

    /**
     * Constructor for "SVR GAMELIST/PLAYERLIST"
     * @param type type of server message --> SVR
     * @param subtype subtype of server message --> GAMELIST/PLAYERLIST
     * @param listDetails TODO: this
     */
    public ServerMessage(MessageType type,
                         MessageSubtype subtype,
                         ArrayList<String> listDetails) {
        this.type = type;
        this.subtype = subtype;
        this.listDetails = listDetails;
    }

    /**
     * TODO: comments
     * @return iets
     */
    public MessageType getType() {
        return type;
    }

    /**
     * TODO: comments
     * @return iets
     */
    public MessageSubtype getSubtype() {
        return subtype;
    }

    /**
     * TODO: comments
     * @return iets
     */
    public MessageAddon getAddon() {
        return addon;
    }

    /**
     * TODO: comments
     * @param key key
     * 
     * @return iets
     */
    public String getDetailValue(String key) {
        return gameDetails.get(key);
    }

    /**
     * TODO: comments
     * @return iets
     */
    public Map<String, String> getGameDetails() {
        return gameDetails;
    }

    /**
     * TODO: comments
     * @return iets
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * TODO: comments
     * @return iets
     */
    public ArrayList<String> getListDetails() {
        return listDetails;
    }

    /**
     * TODO: comments
     * @return iets
     */
    public String getHelpDetails() {
        return helpDetails;
    }
}