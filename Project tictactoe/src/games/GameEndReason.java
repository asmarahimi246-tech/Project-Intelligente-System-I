package games;

public enum GameEndReason {
    NORMAL,
    FORFEITED,
    DISCONNECTED
}

/*
> represents why a game ended
> used for showing game info in (G)UI
> is separate from server WIN/LOSS/DRAW messages
        so can be used in both local AND online games
> used inside/determined by Model class of a game
*/