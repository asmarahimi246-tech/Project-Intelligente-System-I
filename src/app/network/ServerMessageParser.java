package app.network;

import java.util.HashMap;

/**
 * Parses raw server messages into their component parts.
 * <p>
 * Extracts the message Type, Subtype, Addon and remaining details
 * from a server message String. The parsed data is used to
 * make and return a ServerMessage object.
 * <p>
 * A parser instance can be reused to parse multiple messages as
 * previous parsing results are cleared when a new message is
 * processed.
 */
public class ServerMessageParser {
    private HashMap<String, String> gameDetails = new HashMap<String, String>();
    private String[] listDetails;
    private MessageType msgType;
    private MessageSubtype msgSubtype;
    private MessageAddon msgAddon;
    private String svrMessage;
    private final String target = "[(){}<>\\[\\]]";

    /**
     * Constructor calls setMessage() with the given svrMessage as param
     * @param svrMessage server message to parse
     */
    public ServerMessageParser(String svrMessage) {
        setMessage(svrMessage);
    }

    /**
     * Sets the message to be parsed and clears the results from
     * previously parsed messages
     * @param svrMessage the new server message String
     */
    public void setMessage(String svrMessage) {
        this.svrMessage = svrMessage;
        this.details = "";
        this.msgType = null;
        this.msgAddon = null;
        this.msgSubtype = null;
    }

    /**
     * Class to set the MessageType of a server message
     */
    public void setMessageType () {
        String type = svrMessage.split(" ")[0];

        switch (type) {
            case "OK" -> msgType = MessageType.OK;
            case "ERR" -> msgType = MessageType.ERR;
            case "SVR" -> msgType = MessageType.SVR;
            default -> throw new IllegalStateException("Unexpected value: " + svrMessage);
        };
    }

    /**
     * Class to set the MessageSubtype of a server message
     */
    public void setMessageSubtype () {
        String subtype = svrMessage.split(" ")[1];

        switch (subtype) {
            case "GAMELIST" -> msgSubtype = MessageSubtype.GAMELIST;
            case "PLAYERLIST" -> msgSubtype = MessageSubtype.PLAYERLIST;
            case "GAME" -> msgSubtype = MessageSubtype.GAME;
            case "HELP" -> msgSubtype = MessageSubtype.HELP;
            default -> throw new IllegalStateException("Unexpected value: " + subtype);
        };
    }

    /**
     * Class to set the MessageAddon of a server message
     */
    public void setMessageAddon () {
        String addon = svrMessage.split(" ")[2];

        switch (addon) {
            case "WIN" -> msgAddon = MessageAddon.WIN;
            case "LOSS" -> msgAddon = MessageAddon.LOSS;
            case "DRAW" -> msgAddon = MessageAddon.DRAW;
            case "MATCH" -> msgAddon = MessageAddon.MATCH;
            case "YOURTURN" -> msgAddon = MessageAddon.YOURTURN;
            case "MOVE" -> msgAddon = MessageAddon.MOVE;
            case "CHALLENGE" -> msgAddon = MessageAddon.CHALLENGE;
            case "CANCELLED" -> msgAddon = MessageAddon.CANCELLED;
            default -> throw new IllegalStateException("Unexpected value: " + addon);
        }
    }

    /**
     * Class to map the information given after the MessageType, MessageSubtype
     * and MessageAddon of a server message into a string HashMap
     */
    public void setDetails() {
        String subtype = svrMessage.split(" ")[1];

        switch (subtype) {
            case "GAMELIST", "PLAYERLIST" -> setListDetails();
            case "GAME" -> setGameDetails();
        }
    }

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

    private void setListDetails() {
        String details = svrMessage.split(" ")[2];
        listDetails = details.replaceAll(target, "").split(",");
    }

    private void setGameDetails() {
        String[] details = svrMessage.replaceAll(target, "").split(",");
    }


    private ServerMessage parseOk(MessageType msgType) {
        return new ServerMessage(msgType);
    }

    private ServerMessage parseErr(MessageType msgType) {
        getDetails();
        return new ServerMessage(msgType, gameDetails);
    }

    private ServerMessage parseSvr(MessageType msgType) {
        setMessageSubtype();
        return switch (msgSubtype) {
            case GAMELIST, PLAYERLIST, HELP -> {
                getDetails();
                yield new ServerMessage(msgType, gameDetails);
            }
            case GAME ->{
                setMessageAddon();
                setGameDetails();
                yield new ServerMessage(msgType, msgSubtype, msgAddon, gameDetails);
            }
            default -> throw new IllegalStateException("Unexpected value: " + msgSubtype);
        };
    }
    /**
     * Parses a server message and returns its components as a ServerMessage object
     * @param message the server message String to parse
     * @return a new ServerMessage object containing the parsed type, subtype, addon
     * and details
     */
    public ServerMessage parse(String message) {
        setMessage(message);
        setMessageType();

        return switch (msgType) {
            case OK -> parseOk(msgType);
            case ERR -> parseErr(msgType);
            case SVR -> parseSvr(msgType);
        };
    }

    /**
     * Returns the value of the specified key in the parsed message details
     * 
     * @param key the key whose value is requested
     * @return the value of the key
     */
    public String getValue (String key) {
        return gameDetails.get(key);
    }

    public MessageType getMessageType() {
        return msgType;
    }

    public MessageSubtype getMessageSubtype() {
        return msgSubtype;
    }

    public MessageAddon getMessageAddon() { return  msgAddon; }

}