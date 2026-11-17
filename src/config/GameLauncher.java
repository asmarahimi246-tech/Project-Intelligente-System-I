package config;

import config.options.PlayerType;
import games.common.player.BotPlayer;
import games.common.player.LocalPlayer;
import games.common.player.OnlinePlayer;
import games.common.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class GameLauncher {
    private final GameConfig config;
    private final PlayerType p1Type;
    private final PlayerType p2Type;
    private final LinkedList<Player> players = new LinkedList<>();

    public GameLauncher(@NotNull GameConfig config) {
        this.config = config;
        p1Type = config.getP1Type();
        p2Type = config.getP2Type();
    }

    public LinkedList<Player> setPlayers() {

        return switch (config.getMode()) {
            // return list of local players
            // p1 & p2 = LocalPlayer or BotPlayer
            case LOCAL -> setLocalPlayers(p1Type, p2Type);
            // return list of online players
            // p1 = LocalPlayer or BotPlayer
            // p2 = OnlinePlayer or BotPlayer
            case ONLINE -> setOnlinePlayers(p1Type, p2Type);
        };
    }
    // Create local players
    private @NotNull LinkedList<Player> setLocalPlayers(@NotNull PlayerType p1Type,
                                                        PlayerType p2Type) {
        switch (p1Type) {
            case HUMAN -> {
                LocalPlayer p1 = new LocalPlayer();
                players.add(p1);
            }
            case BOT ->  {
                BotPlayer p1 = new BotPlayer();
                players.add(p1);
            }
        }

        switch (p2Type) {
            case HUMAN -> {
                LocalPlayer p2 = new LocalPlayer();
                players.add(p2);
            }
            case BOT ->  {
                BotPlayer p2 = new BotPlayer();
                players.add(p2);
            }
        }
        return players;

    }

    private @NotNull LinkedList<Player> setOnlinePlayers (@NotNull PlayerType p1Type,
                                                          PlayerType p2Type) {
        switch (p1Type) {
            case HUMAN -> {
                LocalPlayer p1 = new LocalPlayer();
                players.add(p1);
            }
            case BOT ->  {
                BotPlayer p1 = new BotPlayer();
                players.add(p1);
            }
        }

        switch (p2Type) {
            case HUMAN -> {
                OnlinePlayer p2 = new OnlinePlayer();
                players.add(p2);
            }
            case BOT ->  {
                BotPlayer p2 = new BotPlayer();
                players.add(p2);
            }
        }
        return players;
    }
}