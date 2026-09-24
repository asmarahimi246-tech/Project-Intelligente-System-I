package app.games.players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import app.common.util.Config;
import app.common.util.ScannerSingleton;
import app.common.widgets.cliMenu.CLIMenu;
import app.games.common.Game;
import app.games.common.Player;
import app.games.common.PlayerState;

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
			super.connectToServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override 
    public PlayerState getState() {
        return super.state;
    }

	@Override
	public void doMove() {
        System.out.println("-----------" + super.name + "-----------");
        // to be sure there isnt any
        try {
            CLIMenu menu = new CLIMenu(name, name);
            menu.clearScreen();
            System.out.println("[client > " + super.name + "] type: move <number (0-9)>");
            Scanner userInput = ScannerSingleton.getInstance();
            String move = userInput.nextLine();
            super.sendCommand(move);
            System.out.println("[server > " + super.name + "] " + super.getAnswer());
        } catch (Exception e) {
            // TODO: handle exception
        }
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
