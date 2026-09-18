package games.kasia.app.games;
import games.kasia.app.games.jarTest.JarTest;
import games.kasia.app.games.randomRaceGame.RandomRaceGame;
import games.kasia.app.games.tikTakToe.TikTakToe;

/**
 * GameTypes
 * 
 * With error handling
 */
public enum GameTypes {
    // List of all game types
    TIKTAKTOE(1, new TikTakToe()), RACEGAME(2, new RandomRaceGame()), JARGAME(3, new JarTest());

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
     * @return the game or
     */
    public static Game getByNumber(int number) {
        for (GameTypes e : values()) {
            if (e.getNumber() == number) {
                return e.getGame();
            }
        }
        throw new ArrayIndexOutOfBoundsException("Invalid game number:" + number);
    }
}