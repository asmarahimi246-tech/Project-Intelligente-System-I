package src.app.network;

import app.network.ServerMessage;
import app.network.ServerMessageParser;

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
        return parser.parse(rawMsg);

    }
}