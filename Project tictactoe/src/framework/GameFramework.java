package framework;

public class GameFramework {
}

/*
> create game of selected GameType
> using a frameworks keeps the checking of game type separate from the GameApp
        which should only focus on running the actual games

EX:
> Game class
    > input = GameType
    > check which game it is --> switch/case
    > return new [insert game type]Model()
*/