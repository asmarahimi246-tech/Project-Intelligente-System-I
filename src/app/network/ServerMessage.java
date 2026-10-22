package app.network;

import java.util.Map;

public class ServerMessage {
    private final MessageType type;
    private final MessageSubtype subtype;
    private final MessageAddon addon;
    private final Map<String, String> details;

    /**
     * Constructor for "OK" server messages
     * @param type type of server message
     */
    public ServerMessage(MessageType type) {
        this.type = type;
        this.subtype = null;
        this.addon = null;
        this.details = null;
    }

    /**
     * Constructor for "ERR" server messages
     * @param type type of server message
     * @param details further details of server message
     */
    public ServerMessage(MessageType type,
                         Map<String, String> details) {
        this.type = type;
        this.subtype = null;
        this.addon = null;
        this.details = details;
    }

    /**
     * Constructor for "HELP" server messages
     * @param type type of server message
     * @param subtype subtype of server message
     * @param details further details of server message
     */
    public ServerMessage(MessageType type,
                         MessageSubtype subtype,
                         Map<String, String> details) {
        this.type = type;
        this.subtype = subtype;
        this.addon = null;
        this.details = details;
    }

    /**
     * Constructor for "SVR" server messages
     * @param type type of server message
     * @param subtype subtype of server message
     * @param addon addon to subtype of server message
     * @param details further details of server message
     */
    public ServerMessage(
            MessageType type,
            MessageSubtype subtype,
            MessageAddon addon,
            Map<String, String> details) {
        this.type = type;
        this.subtype = subtype;
        this.addon = addon;
        this.details = Map.copyOf(details);
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

    public String getValue(String key) {
        return details.get(key);
    }

    public Map<String, String> getDetails() {
        return details;
    }
}