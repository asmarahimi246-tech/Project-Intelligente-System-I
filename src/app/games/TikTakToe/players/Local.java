package app.games.TikTakToe.players;

import app.common.util.Logger;
import app.common.util.ScannerSingleton;
import app.common.widgets.cliInputFields.CLIIntegerField;
import app.games.TikTakToe.TikTakToe;
import app.games.TikTakToe.common.AbstractTikTakToePlayer;

/**
 * TikTakToePlayer
 */
public class Local extends AbstractTikTakToePlayer {
    /**
     * Constructor
     * 
     * @param name name of the player used on server login
     * @param tikTakToe 
     */
    public Local(String name, TikTakToe tikTakToe) {
        super(name, tikTakToe);
    }

    @Override
    public void doMove() {
        // input setup
        CLIIntegerField field = new CLIIntegerField.Builder(ScannerSingleton.getInstance(), "This tile doesnt exist on the board")
            .min(1)
            .max(9)
            .build();
        
        boolean tileSet = false;
        int input = -1;

        // render
        field.print();

        // get valid input
        while (!tileSet) {
            input = field.getInput();
            input -= 1;
            Logger.logDebug("[" + super.name + "] recieved an input of " + (input + 1) + " from the cli");

            // place a char on the tile
            tileSet = super.game.setTile(input, super.getBoardChar());
        }

        // send the input
        super.client.sendCommand("move " + input);
    }
}
