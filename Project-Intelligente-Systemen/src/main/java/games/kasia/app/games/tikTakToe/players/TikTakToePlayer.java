package games.kasia.app.games.tikTakToe.players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import games.kasia.app.client.common.Config;
import games.kasia.app.client.common.ScannerSingleton;
import games.kasia.app.games.Game;
import games.kasia.app.games.common.Player;

/**
 * TikTakToePlayer
 */
public class TikTakToePlayer extends Player {
    private PlayerState state;
    /**
     * Constructor
     * 
     * @param name name of the player used on server login
     * @param game the game
     */
    public TikTakToePlayer(String name, Game game) {
        super();
        super.name = name;
        super.game = game;
        Socket socket = null;
        if (super.game.isOnline()) {
            throw new UnsupportedOperationException("Unimplemented method 'doMove'");
        } else {
            try {
				socket = new Socket("localhost", Config.LOCALPORT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        super.socket = socket;

        try {
			this.connectToServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

	@Override
	public void doMove() {
        System.out.println("-----------" + super.name + "-----------");
        // to be sure there isnt any
        System.out.println(super.getAnswer());
        System.out.println(super.getAnswer());

        Scanner userInput = ScannerSingleton.getInstance();
        String move = userInput.nextLine();
        super.sendCommand(move);
        System.out.println(super.getAnswer());
    }

	@Override
	protected void connectToServer() throws IOException {
        reader = new BufferedReader(new InputStreamReader(super.socket.getInputStream()));
        writer = new PrintWriter(super.socket.getOutputStream(), true);
        super.printLongResponse(2);

        super.sendCommand("login " + super.name);
        System.out.println(super.getAnswer());
	}

    /**
     * Subscribe to the game
     */
    @Override
    public void subscribe() {
        super.sendCommand("subscribe " + game.toString());
        System.out.println(super.getAnswer());
    }


	@Override
	public void disconnect() {
		try {
            super.sendCommand("bye");
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
