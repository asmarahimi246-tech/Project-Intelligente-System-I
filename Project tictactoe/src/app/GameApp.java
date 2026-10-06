package app;

import framework.GameType;
import players.Player;

public class GameApp {
  GameType game = new GameType();
  Player player = new Player();

  startGame(game, player) {
      // return model of chosen game
      // run game in chosen mode
  }
  game = gameframewrork new game
  game.run()


}

/*
> runs app and controls app flow

EX:
> show main menu
> ask user what they want to do
    > execute given command such as:
        > start selected game
> return to menu or exit

> startGame()
    > choose game & mode
    > create chosen game
    > start game
 */