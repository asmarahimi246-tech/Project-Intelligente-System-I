package games;

public interface Move {
}

/*
> not a specific Move object cus every Player type implements Move differently
> generic move object, makes move logic/implementation in rest of app easier
> specific game's moves defined per game in a separate class.
        smth like [insert game's name]Move --> TicTacToeMove
 */