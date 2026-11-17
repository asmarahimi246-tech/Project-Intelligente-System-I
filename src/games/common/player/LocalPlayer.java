package games.common.player;

import games.common.game.Move;

public class LocalPlayer implements Player {

    // TODO: get input from cli or gui
    @Override
    public Move getMove() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void setName() {
        System.out.println("Enter a name: ");
//        String name = ui.getInput();
    }
}