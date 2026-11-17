package games.tictactoe;

import games.common.game.GameController;
import games.common.game.GameModel;
import games.common.game.GameView;

public class TicTacToeController extends GameController {
    public TicTacToeController(GameModel model,
                               GameView view) {

        super(model, view);
    }

    @Override
    public void play() {
    // TODO: get input from players via UI & make Model check moves/game state
    //  then have View update accordingly.
    //  EX: checkMove() -> make model check if valid move
    //               -> if so update View with move added


        while (!model.isGameOver()) {
            view.displayBoard(model.getBoard());
            // Player player = getCurrentPlayer();

            view.displayPlayerToMove("[player name here]");

        }

    }
}

//    public void playGame() {
//        while (!isOver()) {
//            turn();
//        }
//    }
//
//    public void turn() {
//        while (true) {
//            int input = view.readMove();
//            input -= 1;
//
//            if (model.placeMove(input, player1)) {
//                view.printBoard();
//
//            }
//        }
//    }