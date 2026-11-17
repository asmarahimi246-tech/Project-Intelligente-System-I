package games.common.player;

import games.common.game.Move;

public interface Player {

    Move getMove();

    String getName();

    void setName();

}