package app.games.tictactoe;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import app.games.tictactoe.players.OnlineOpponent;
import app.games.tictactoe.players.moveStrategies.AIStrategy;
import app.games.tictactoe.players.moveStrategies.UserStrategy;
import app.network.Client;
import app.util.Config;
import app.util.ScannerSingleton;
import app.games.tictactoe.players.OnlinePlayer;
import app.games.tictactoe.players.LocalPlayer;
import app.games.framework.game.GameBuilder;
import app.games.framework.game.GameMode;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerOption;

/**
 * builder
 * builds the game
 */
public class TicTacToeBuilder extends GameBuilder<TicTacToeGame,TicTacToeBuilder> {
    private TicTacToePage page = new TicTacToePage();
    private Client shared;

    /**
     * Constructor
     */
    public TicTacToeBuilder() {
        super();
        super.playersToAdd = 2;
    }

    @Override
    public TicTacToeBuilder setMode(GameMode mode) {
        if (mode == GameMode.ONLINE) {
            try {
                // make the socket for online gaming
                Socket socket;
                if (Config.DEBUG) {
                    Scanner scanner = ScannerSingleton.getInstance();
                    System.out.println("want to connect to localhost: (y/n)");
                    String answer = scanner.nextLine();
                    if (answer.equals("y")) {
                        socket = new Socket("localhost", Config.LOCALPORT);
                    } else {
                        socket = new Socket(Config.ONLINEIP, Config.ONLINEPORT);
                    }
                } else {
                    socket = new Socket(Config.ONLINEIP, Config.ONLINEPORT);
                }

                shared = new Client(socket);
            } catch (Exception e) {
                // TODO: handle exception
            }
         }
        return super.setMode(mode);
    }

    @Override
    public String toString() {
        return "Tic Tac Toe Builder";
    }

    @Override
    protected TicTacToeBuilder self() {
        return this;
    }

    // TODO: this
    @Override
    public TicTacToeGame build() {
        // setup playes
        ArrayList<Player> players = new ArrayList<Player>();

        
        // make players
        // types: Remote, Local
        Player one = super.players.get(0).build();
        // types: Online, Local
        Player two = super.players.get(1).build();

        // Online
        if (super.mode == GameMode.ONLINE) {
            // setup online player
            if (one instanceof OnlineOpponent onlineOpponent && two instanceof OnlinePlayer onlinePlayer) {

                // server based order
                // by waiting for Match message
                boolean remotePlayerFirst = onlineOpponent.isFirst();
                if(remotePlayerFirst) {
                    players.add(onlineOpponent);
                    players.add(onlinePlayer);
                } else {
                    players.add(onlinePlayer);
                    players.add(onlineOpponent);
                }
            }

        // Local
        } else if (super.mode == GameMode.LOCAL) {
            // random order
            Random random = new Random();
            if(random.nextBoolean()) {
                players.add(one);
                players.add(two);
            } else {
                players.add(two);
                players.add(one);
            }
        }

        // make and set the game
        TicTacToeGame game = new TicTacToeGame(page, players);
        page.setGame(game);

        return game;
    }

    @Override 
    public TicTacToeBuilder addDefaultPlayers(){
        // add remote player if online
        if (super.mode == GameMode.ONLINE) {

            PlayerOption<OnlineOpponent> remote = new PlayerOption<OnlineOpponent>("remote", () -> new OnlineOpponent(shared));
            super.addPlayer(remote);
        }
        // do nothing for local
        // TODO: tournament

        return this;
    }

    @Override
    public List<PlayerOption<? extends Player>> getPlayerOptions() {
        List<PlayerOption<?>> toReturn = null;

        // online players
        if (super.mode == GameMode.ONLINE) {


            toReturn = List.of(
                new PlayerOption<OnlinePlayer>("player", () -> new OnlinePlayer(new UserStrategy(page), shared)),
                new PlayerOption<OnlinePlayer>("ai", () -> new OnlinePlayer(new AIStrategy(), shared))
                );

        // local players
        } else if (super.mode == GameMode.LOCAL) {
            toReturn = List.of(
                new PlayerOption<LocalPlayer>("player", () -> new LocalPlayer(new UserStrategy(page))),
                new PlayerOption<LocalPlayer>("ai", () -> new LocalPlayer(new AIStrategy()))
                );
        }
        // do nothing for tournament
        // TODO: tournament

        return toReturn;
    }

}