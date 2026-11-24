package app.games.reversi;

import app.games.framework.exceptions.IlligalPlayerOptionException;
import app.games.framework.game.GameBuilder;
import app.games.framework.game.GameMode;
import app.games.framework.player.Player;
import app.games.framework.player.PlayerOption;
import app.games.tictactoe.TicTacToeGame;
import app.games.tictactoe.TicTacToePage;
import app.games.tictactoe.players.OnlineOpponent;
import app.games.tictactoe.players.OnlinePlayer;
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
        return null;
    }

    @Override
    public ReversiGame build() {
        return null;
    }

    @Override
    protected ArrayList<Player> setupLocalPlayers(ArrayList<Player> builtPlayers) {
        return null;
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
        opponent.waitForMatch();

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
        // im very usefull
        return setupTournamentPlayers(builtPlayers);
    }

    @Override
    public List<PlayerOption<? extends Player>> getPlayerOptions() {
        return List.of();
    }

    @Override
    public void close() {
        if (mode == GameMode.ONLINE || mode == GameMode.TOURNAMENT) {
            client.close();
        }
    }
}
