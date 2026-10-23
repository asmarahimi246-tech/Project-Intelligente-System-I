package app.network;

/**
 * Handles server messages by parsing the raw messages into ServerMessage objects.
 */
public class MessageHandler {
    private final ServerMessageParser parser;

    public MessageHandler() {
        this.parser = new ServerMessageParser();
    }

    public ServerMessage handleMessage(String rawMsg) {
        return parser.parse(rawMsg);

    }
}