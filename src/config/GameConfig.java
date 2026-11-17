package config;

import config.options.GameMode;
import config.options.GameType;
import config.options.PlayerType;

public class GameConfig {
    private final GameMode mode;
    private final GameType type;
    private final PlayerType player1;
    private final PlayerType player2;



    public GameConfig(GameMode mode,
                      GameType type,
                      PlayerType player1,
                      PlayerType player2) {
        this.mode = mode;
        this.type = type;
        this.player1 = player1; // you/user
        this.player2 = player2; // opponent

    }

    public GameType getType() { return type; }

    public PlayerType getP1Type() { return player1; }

    public PlayerType getP2Type() { return player2; }

    public GameMode getMode() { return mode; }
}