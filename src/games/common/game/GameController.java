package games.common.game;

public abstract class GameController {
    protected static GameModel model;
    protected static GameView view;

    public GameController(GameModel model, GameView view) {
        GameController.model = model;
        GameController.view = view;
    }

    // play logic of the game
    public abstract void play();
}

//Start and manage the game loop
//Get input from the user
//Send moves to the Model
//Ask the Model for the current game state
//Tell the View what to display
//Handle invalid input