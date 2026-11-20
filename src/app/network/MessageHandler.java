package app.network;

/**
 * Handles server messages by parsing the raw messages into ServerMessage objects.
 * reuses 1 parser
 */
public class MessageHandler {
    private final ServerMessageParser parser;

    public MessageHandler() {
        this.parser = new ServerMessageParser();
    }

    public ServerMessage handleMessage(String rawMsg) {
        if (rawMsg == null || rawMsg.isBlank()) {
            throw new IllegalArgumentException(
                    "Cannot parse a null or empty server message"
            );
        }
        return parser.parse(rawMsg);
    }
}