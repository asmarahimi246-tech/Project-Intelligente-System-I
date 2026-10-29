package app.games.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.games.tictactoe.players.OnlineOpponent;
import app.games.tictactoe.players.moveStrategies.AggresiveAIStrategy;
import app.games.tictactoe.players.moveStrategies.UserStrategy;
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

    /**
     * Constructor
     */
    public TicTacToeBuilder() {
        super();
        super.playersToAdd = 2;
    }

    @Override
    public String toString() {
        return "Tic Tac Toe Builder";
    }

    @Override
    protected TicTacToeBuilder self() {
        return this;
    }


    @Override 
    public TicTacToeBuilder addDefaultPlayers(){
        // add remote player if online or tournament
        if (mode == GameMode.ONLINE || mode == GameMode.TOURNAMENT) {
            PlayerOption<OnlineOpponent> remote = new PlayerOption<OnlineOpponent>("remote", () -> new OnlineOpponent(client));
            addPlayer(remote);
        }

        return this;
    }

    @Override
    public List<PlayerOption<? extends Player>> getPlayerOptions() {
        List<PlayerOption<?>> toReturn = null;

        // online players
        if (mode == GameMode.ONLINE) {
            toReturn = List.of(
                new PlayerOption<OnlinePlayer>("player", () -> new OnlinePlayer(new UserStrategy(page), client)),
                new PlayerOption<OnlinePlayer>("aggresive ai", () -> new OnlinePlayer(new AggresiveAIStrategy(), client))
                );

        // local players
        } else if (mode == GameMode.LOCAL) {
            toReturn = List.of(
                new PlayerOption<LocalPlayer>("player", () -> new LocalPlayer(new UserStrategy(page))),
                new PlayerOption<LocalPlayer>("aggresive ai", () -> new LocalPlayer(new AggresiveAIStrategy()))
                );

        // tournament players
        } else if (mode == GameMode.TOURNAMENT) {
            toReturn = List.of(
                new PlayerOption<OnlinePlayer>("player", () -> {
                    OnlinePlayer player = new OnlinePlayer(new UserStrategy(page), client);
                    // generate name before build so its used in multiple games
                    player.setName(client.getName());
                    return player;
                    }),
                new PlayerOption<OnlinePlayer>("aggresive ai", () -> {
                    OnlinePlayer player = new OnlinePlayer(new AggresiveAIStrategy(), client);
                    // generate name before build so its used in multiple games
                    player.setName(client.getName());
                    return player;
                    })
            );
        }

        return toReturn;
    }

    @Override 
    public void close() {
        if (mode == GameMode.ONLINE || mode == GameMode.TOURNAMENT) {
            client.close();
        }
    }

    @Override
    public TicTacToeGame build() {
        // setup playes
        ArrayList<Player> players = new ArrayList<Player>();

        
        // make players
        // types: Online, Local
        Player two = super.players.get(1).build();
        // types: Opponent, Local
        Player one = super.players.get(0).build();



        // Online
        if (super.mode == GameMode.ONLINE) {
            // setup online player
            if (one instanceof OnlineOpponent onlineOpponent && two instanceof OnlinePlayer onlinePlayer) {

                // login player
                onlinePlayer.subscribe();

                // wait for match
                onlineOpponent.waitForMatch();

                // server based order
                // by waiting for Match message
                boolean onlineOpponentFirst = onlineOpponent.isFirst();
                if(onlineOpponentFirst) {
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

            // Tournament
        } else if (super.mode == GameMode.TOURNAMENT) {
            // setup online player
            if (one instanceof OnlineOpponent onlineOpponent && two instanceof OnlinePlayer onlinePlayer) {
                // wait for match
                onlineOpponent.waitForMatch();

                // server based order
                // by waiting for Match message
                boolean onlineOpponentFirst = onlineOpponent.isFirst();
                if(onlineOpponentFirst) {
                    players.add(onlineOpponent);
                    players.add(onlinePlayer);
                } else {
                    players.add(onlinePlayer);
                    players.add(onlineOpponent);
                }
            }

        }

        // make and set the game
        TicTacToeGame game = new TicTacToeGame(page, players);
        page.setGame(game);

        return game;
    }
}