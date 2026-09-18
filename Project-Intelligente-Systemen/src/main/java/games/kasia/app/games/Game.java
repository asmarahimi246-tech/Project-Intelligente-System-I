package games.kasia.app.games;

/**
 * Game
 */
public class Game {
    protected String name;

    /**    (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override 
    public String toString(){
        return this.name;
    }

    /**
     * runs the game
     */
    public void run(){

    }
    
    /**
     * Closes game in x seconds
     */
    protected void closeInSeconds() {
        int seconds = 5;

        // for testing
        System.out.println("game ends in " + seconds + "s");
        try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}