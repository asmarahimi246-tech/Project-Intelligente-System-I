package games.kasia.app.games.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import games.kasia.app.games.Game;

/**
 * Game
 */
public abstract class Player {
    protected String name;
    protected Game game;
    protected BufferedReader reader;
    protected PrintWriter writer;
    protected Socket socket;
    

    /**    (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override 
    public String toString(){
        return this.name;
    }

    /**
     * Does a move
     */
    public abstract void doMove();

    /**
     * connects to a server
     */
    protected abstract void connectToServer() throws IOException;

    /**
     * disconnects server
     */
    public  abstract void disconnect();

    /**
     * sends a command to the socket
     * 
     * @param command
     */
    public void sendCommand(String command) {
        System.out.println("[" + this.toString() + "] " + command);
        writer.println(command);
    }

    /**
     * receives from server
     */
    public String getAnswer() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * I dont like this
     * TODO: this
     * 
     * @param lines
     * @return
     */
    protected void printLongResponse(int lines) {
        try {
            String line;
            while (lines > 0) {
                line = reader.readLine();
                if (line != null) {
                    System.out.println("[" + this.toString() + "] " + "[server]" + line);
                } else {
                    // Stream closed
                    break;
                }
                lines--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}