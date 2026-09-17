package games.kasia.app.games;
import games.kasia.app.games.randomRaceGame.RandomRaceGame;
import games.kasia.app.games.tikTakToe.TikTakToe;

/**
 * GameTypes
 */
public enum GameTypes {

    TIKTAKTOE(1, new TikTakToe()), RACEGAME(2, new RandomRaceGame());

    private final int gameNumber;
    private final Game game;

    GameTypes(int gameNumber, Game game){
        this.gameNumber = gameNumber;
        this.game = game;
    }

    /**
     * gets the game number
     * 
     * @return the game number
     */
    public int getNumber(){
        return  this.gameNumber;
    }

    /**
     * gets the game
     * 
     * @return the game
     */
    public Game getGame(){
        return  this.game;
    }

    /**
     * gets the game by a number
     * 
     * @param number the number
     * 
     * @return the game
     */
    public static Game getByNumber(int number) {
        for (GameTypes e : values()) {
            if (e.getNumber() == number) {
                return e.getGame();
            }
        }
        return null;
    }
}