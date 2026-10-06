package players;

import games.Move;

public interface Player {

    String getName();

    public abstract Move getMove(Board board);
}

/*
> not a specific player --> INTERFACE
> what all players have in common

EX:
> player name --> getName()
*/