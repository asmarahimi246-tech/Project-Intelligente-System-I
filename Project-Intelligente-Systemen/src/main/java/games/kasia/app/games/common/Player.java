package games.kasia.app.games.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.management.RuntimeErrorException;

import games.kasia.app.games.Game;
import games.kasia.app.games.common.PlayerStates;

/**
 * Game
 */
public abstract class Player {
    protected String name;
    protected Game game;
    protected BufferedReader reader;
    protected PrintWriter writer;
    protected Socket socket;
    protected PlayerStates state;
    

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
    protected void connectToServer() throws IOException{
        reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        writer = new PrintWriter(this.socket.getOutputStream(), true);
        this.printLongResponse(2);
        this.state = PlayerStates.INITIALIZED;
    }

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
        System.out.println("[" + this.toString() + " > server] " + command);
        writer.println(command);
    }


    /**
     * receives from server
     * also checks type
     * 
     * Deepai used to figure out what exception to throw
     * 
     * there are a lot and i dont remember all
     * 
     * @param customStopString checks if the output contains the string
     * @return the answer
     * @throws IOException an exception
     */
    public String getAnswer(String customStopString) throws IOException {
        return getAnswerFunc(customStopString);
    }

    /**
     * receives from server
     * also checks type
     * 
     * Deepai used to figure out what exception to throw
     * 
     * there are a lot and i dont remember all
     * @return the answer
     * @throws IOException an exception
     */
    public String getAnswer() throws IOException {
        return getAnswerFunc(null);
    }

    /**
     * receives from server
     * also checks type
     * 
     * Deepai used to figure out what exception to throw
     * 
     * there are a lot and i dont remember all
     */
    private String getAnswerFunc(String customStopString) throws IOException {
        String answer;
        try {
            boolean svrMessages = true;
            answer = reader.readLine();
                            // why are SVR messages sometimes send before OK/ERR and sometimes after
            while (svrMessages) {
                if (answer.contains("OK")) {
                    svrMessages = false;
                } else if (answer.contains("ERR")) {
                    System.err.println("[error] " + answer);
                    game.sendCloseSignal();
                    throw new IOException(answer);
                } else if (answer.contains("SVR")) {
                    game.processMessage(answer);
                    answer = reader.readLine();
                } else if (answer.contains(customStopString)) {
                    svrMessages = false;
                } else {
                    answer = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return answer;
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

    public abstract PlayerStates getState();

    /**
     * Logs in
     */
    public void login() {
        this.sendCommand("login " + this.name);
        // error handling
        try {
            System.out.println(this.getAnswer());
            this.state = PlayerStates.LOGGED_IN;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Subscribe to the game
     */
    public void subscribe() {
        this.sendCommand("subscribe " + game.toString());
        // error handling
        try {
            System.out.println(this.getAnswer());
            this.state = PlayerStates.SUBSCRIBED;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}