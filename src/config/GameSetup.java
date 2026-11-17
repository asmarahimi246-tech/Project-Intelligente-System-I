package config;

import config.options.GameMode;
import config.options.GameType;
import config.options.PlayerType;

public class GameSetup {
    private GameMode mode;
    private GameType gameType;
    private PlayerType p1Type;
    private PlayerType p2Type;
//    private SetupStep currentStep = SetupStep.MODE_SELECTED;

    public void setMode(GameMode mode) {
        this.mode = mode;
//        advance();
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
//        advance();
    }

    public void setP1Type(PlayerType p1) {
        this.p1Type = p1;
//        advance();
    }

    public void setP2Type(PlayerType p2) {
        this.p2Type = p2;
//        advance();
    }

//    public void advance() {
//        currentStep = switch (currentStep) {
//            case MODE_SELECTED -> SetupStep.GAME_SELECTED;
//            case GAME_SELECTED -> SetupStep.P1_SELECTED;
//            case P1_SELECTED -> SetupStep.P2_SELECTED;
//            case P2_SELECTED-> SetupStep.CONFIRMED;
//            case CONFIRMED, COMPLETED -> SetupStep.COMPLETED;
//        };
//    }

    public GameConfig build() {

        if (mode == null ||
                gameType == null ||
                p1Type == null ||
                p2Type == null) {

            throw new IllegalStateException(
                    "Game configuration is incomplete"
            );
        }

        return new GameConfig(
                mode,
                gameType,
                p1Type,
                p2Type
        );
    }

    public void reset() {
        mode = null;
        gameType = null;
        p1Type = null;
        p2Type = null;
    }
}