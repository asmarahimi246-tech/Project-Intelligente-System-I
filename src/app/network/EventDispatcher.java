
package app.network;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventDispatcher implements Runnable {

    private final Client client;

    private final List<ServerEventListener> listeners =
            new CopyOnWriteArrayList<>();

    private final AtomicBoolean running =
            new AtomicBoolean(true);

    private volatile Thread dispatcherThread;

    public EventDispatcher(Client client) {
        this.client = client;
    }

    public void addListener(ServerEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ServerEventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        dispatcherThread = Thread.currentThread();

        try {
            while (running.get()) {
                ServerMessage message;

                try {
                    message = client.getNextSvrMessage();
                } catch (IllegalStateException e) {
                    // Client connection ended and the queue is empty.
                    break;
                }

                if (!running.get()) {
                    break;
                }

                for (ServerEventListener listener : listeners) {
                    if (!running.get()) {
                        break;
                    }

                    try {
                        listener.onServerEvent(message);
                    } catch (RuntimeException e) {
                        System.err.println(
                                "Event listener failed: "
                                        + e.getMessage());
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            running.set(false);
        }
    }

    public void stop() {
        running.set(false);

        Thread thread = dispatcherThread;

        if (thread != null) {
            thread.interrupt();
        }
    }
}