package app.network;

/**
 * this interface owns the loop that reads events from the client.
 * <p>
 * this prevents different threads that use the client.getNextSvrMessage()
 * method from consuming each other's events.
 * <p>
 * EX: Game Controller and GUI Controller
 */
public interface ServerEventListener {
    void onServerEvent(ServerMessage message);
}