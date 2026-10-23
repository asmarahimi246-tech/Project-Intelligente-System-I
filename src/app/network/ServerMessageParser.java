package app.network;

import app.network.MessageAddon;
import app.network.MessageSubtype;
import app.network.MessageType;

import java.util.ArrayList;
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
    private ArrayList<String> listDetails = new ArrayList<>();
    private MessageType msgType;
    private MessageSubtype msgSubtype;
    private MessageAddon msgAddon;
    private String svrMessage;
    private String errorDetails;
    private String helpDetails;

    public ServerMessageParser() {}

//    /**
//     * Calls setMessage() with the given svrMessage as param.
//     * @param svrMessage raw server message to parse
//     */
//    public ServerMessageParser(String svrMessage) {
//        setMessage(svrMessage);
//    }

    /**
     * Clear all parsed data.
     */
    private void reset() {
        gameDetails.clear();
        listDetails.clear();

        msgType = null;
        msgSubtype = null;
        msgAddon = null;
        errorDetails = null;
        helpDetails = null;
    }

    /**
     * Sets the message to be parsed and clears the results from
     * previously parsed messages.
     * @param svrMessage the new raw server message
     */
    public void setMessage(String svrMessage) {
        this.svrMessage = svrMessage;
    }

    /**
     * Sets the MessageType of a server message
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
     * Sets the MessageSubtype of a server message
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
     * Sets the MessageAddon of a server message
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
            case "CHALLENGE" -> {
                if (svrMessage.split(" ")[3].equals("CANCELLED")) {
                    msgAddon = MessageAddon.CANCELLED;
                } else {
                    msgAddon = MessageAddon.CHALLENGE;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + addon);
        }
    }

    /**
     * Set server message detail information
     */
    public void setDetails() {
        switch (msgSubtype) {
            case GAMELIST, PLAYERLIST -> setListDetails();
            case GAME -> setGameDetails();
            case HELP -> setHelpDetails();
        }
    }

    private void setListDetails() {
        String listItem;
        String[] rawDetails = svrMessage.substring(svrMessage.indexOf("[") + 1, svrMessage.indexOf("]")).split(",");

        for (String item : rawDetails) {
            listItem = item.replace("\"", "").trim();

            listDetails.add(listItem);
        }
    }

    private void setGameDetails() {
        String key;
        String value;
        String[] rawDetails = svrMessage.substring(svrMessage.indexOf("{") + 1, svrMessage.indexOf("}")).split(", ");

        for (String param : rawDetails) {
            key = param.split(":")[0];
            value = param.split(":")[1].replace("\"", "").trim();

            gameDetails.put(key, value);
        }
    }

    private void setHelpDetails() {
        helpDetails = svrMessage.substring(svrMessage.indexOf(" " + 2));
    }

    private void setErrDetails() {
        errorDetails = svrMessage.substring(svrMessage.indexOf(" ") + 1);
    }

    private ServerMessage parseErr(MessageType msgType) {
        setErrDetails();
        return new ServerMessage(msgType, errorDetails);
    }

    private ServerMessage parseSvr(MessageType msgType) {
        setMessageSubtype();
        return switch (msgSubtype) {
            case GAMELIST, PLAYERLIST -> {
                setDetails();
                yield new ServerMessage(msgType, msgSubtype, listDetails);
            }
            case GAME ->{
                setMessageAddon();
                setGameDetails();
                yield new ServerMessage(msgType, msgSubtype, msgAddon, gameDetails);
            }
            case HELP -> {
                setHelpDetails();
                yield new ServerMessage(msgType, msgSubtype, helpDetails);
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
        reset();
        setMessage(message);
        setMessageType();

        return switch (msgType) {
            case OK -> new ServerMessage(msgType);
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

    public MessageSubtype getMessageSubtype() { return msgSubtype; }

    public MessageAddon getMessageAddon() { return  msgAddon; }

    public String getErrDetails() { return errorDetails; }

}