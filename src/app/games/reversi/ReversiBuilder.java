package app.games.reversi;

import app.games.framework.exceptions.BuildFailedException;
import app.games.framework.exceptions.IlligalPlayerOptionException;
import app.games.framework.exceptions.MatchNotFoundException;
import app.games.framework.game.GameBuilder;
import app.games.framework.game.GameMode;
import app.games.framework.game.GameState;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerOption;
import app.games.reversi.players.LocalPlayer;
import app.games.reversi.players.OnlineOpponent;
import app.games.reversi.players.OnlinePlayer;
import app.games.reversi.players.moveStrategies.UserStrategy;
import app.util.Config;
import app.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * ReversiBuilder
 */
public class ReversiBuilder extends GameBuilder<ReversiGame, ReversiBuilder> {

    public ReversiBuilder() {
        super();
        super.playersToAdd = 2;
        super.gameName = "reversi";
        super.page = new ReversiPage();
    }

    @Override
    protected ReversiBuilder self() {
        return this;
    }

    @Override
    protected ReversiBuilder addDefaultPlayers() {
        // add remote player if online or tournament
        if (mode == GameMode.ONLINE || mode == GameMode.TOURNAMENT) {
            PlayerOption<OnlineOpponent> remote = new PlayerOption<OnlineOpponent>("remote", () -> new OnlineOpponent(client));
            addPlayer(remote);
        }

        return this;
    }

    @Override
    public ReversiGame build() {
        // build players
        ArrayList<Player> builtPlayers = new ArrayList<>(2);

        for (PlayerOption<? extends Player> playerOption : players) {
            builtPlayers.add(playerOption.build());
        }

        // setup players an turn limit
        long turnLimit = Config.DEFAULT_TIMEOUT;
        ArrayList<Player> setupPlayers;
        switch (mode) {
            case LOCAL:
                setupPlayers = new ArrayList<Player>(setupLocalPlayers(builtPlayers));
                break;

            case ONLINE:
                setupPlayers = new ArrayList<Player>(setupOnlinePlayers(builtPlayers));
                turnLimit = Config.ONLINE_TURNLIMIT;
                break;

            case TOURNAMENT:
                setupPlayers = new ArrayList<Player>(setupTournamentPlayers(builtPlayers));
                turnLimit = Config.TOURNAMENT_TURNLIMIT;
                break;
        
            default:
                throw new IllegalStateException("mode doesnt exist", null);
        }

        // build game
        ReversiGame game = new ReversiGame((ReversiPage) page, setupPlayers, turnLimit);
        page.setGame(game);

        return game;
    }

    @Override
    protected ArrayList<Player> setupLocalPlayers(ArrayList<Player> builtPlayers) {
        ArrayList<Player> setupPlayers = new ArrayList<Player>(builtPlayers);

        // give local players a logical name
        setupPlayers.get(0).setName("Player 1");
        setupPlayers.get(1).setName("Player 2");

        return setupPlayers;
    }

    @Override
    protected ArrayList<Player> setupOnlinePlayers(ArrayList<Player> builtPlayers) {
        ArrayList<Player> setupPlayers = new ArrayList<Player>(2);

        // filter and get players by type
        OnlinePlayer online = builtPlayers.stream().filter(player -> player instanceof OnlinePlayer)
                .map(player -> (OnlinePlayer) player)
                .findFirst()
                .orElseThrow(() -> new IlligalPlayerOptionException("An online game needs an online player"));

        OnlineOpponent opponent = builtPlayers.stream().filter(player -> player instanceof OnlineOpponent)
                .map(player -> (OnlineOpponent) player)
                .findFirst()
                .orElseThrow(() -> new IlligalPlayerOptionException("An online game needs an online opponent"));

        // set logical display name
        online.setName("you");

        // wait for match
        // wait for match
        try {
            opponent.waitForMatch();
        } catch(MatchNotFoundException e) {
            state = GameState.failed(e);
            throw new BuildFailedException("No match found");
        }

        // server based order
        // by waiting for Match message
        boolean opponentFirst = opponent.isFirst();
        if(opponentFirst) {
            Logger.logDebug("opponent first");
            setupPlayers.add(opponent);
            setupPlayers.add(online);
        } else {
            Logger.logDebug("player first");
            setupPlayers.add(online);
            setupPlayers.add(opponent);
        }

        return setupPlayers;
    }

    @Override
    protected ArrayList<Player> setupTournamentPlayers(ArrayList<Player> builtPlayers) {
        return setupTournamentPlayers(builtPlayers);
    }

    @Override
    public List<PlayerOption<? extends Player>> getPlayerOptions() {
        List<PlayerOption<?>> toReturn = null;

        // online players
        if (mode == GameMode.ONLINE) {
            toReturn = List.of(
                    new PlayerOption<OnlinePlayer>("player", () -> new OnlinePlayer(new UserStrategy((ReversiPage) page), client)) //,
                    //new PlayerOption<OnlinePlayer>("ai", () -> new OnlinePlayer(new ReversiAiStrategy(), client)),
                    //new PlayerOption<OnlinePlayer>("random ai", () -> new OnlinePlayer(new RandomAiStrategy(), client))
            );

            // local players
        } else if (mode == GameMode.LOCAL) {
            toReturn = List.of(
                    new PlayerOption<LocalPlayer>("player", () -> new LocalPlayer(new UserStrategy((ReversiPage) page))) //,
                    //new PlayerOption<LocalPlayer>("aggresive ai", () -> new LocalPlayer(new ReversiAiStrategy())),
                    //new PlayerOption<LocalPlayer>("random ai", () -> new LocalPlayer(new RandomAiStrategy()))
            );

            // tournament players
        } else if (mode == GameMode.TOURNAMENT) {
            toReturn = List.of(
                    new PlayerOption<OnlinePlayer>("player", () -> {
                        OnlinePlayer player = new OnlinePlayer(new UserStrategy((ReversiPage) page), client);
                        // generate name before build so its used in multiple games
                        player.setName(client.getName());
                        return player;
                    }) //,
//                    new PlayerOption<OnlinePlayer>("ai", () -> {
//                        OnlinePlayer player = new OnlinePlayer(new ReversiAiStrategy(), client);
//                        // generate name before build so its used in multiple games
//                        player.setName(client.getName());
//                        return player;
//                    }),
//                    new PlayerOption<OnlinePlayer>("random ai", () -> {
//                        OnlinePlayer player = new OnlinePlayer(new RandomAiStrategy(), client);
//                        // generate name before build so its used in multiple games
//                        player.setName(client.getName());
//                        return player;
//                    })
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
}

