package app.games.common;

import java.util.Scanner;

/**
 * Game
 */
public abstract class Game {
    protected String name;
    protected boolean isOnline = false;
    protected boolean gameRunning = false;

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
     * @param userInput 
     */
    public abstract void run();
    
    /**
     * Closes game in x seconds
     */
    protected void closeInSeconds() {
        int seconds = 5;

        // for testing
        System.out.println("[info] game ends in " + seconds + "s");
        try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Check if its an online game
     */
	public boolean isOnline() {
        return this.isOnline;
    }

    public abstract void processMessage(String answer);

    public abstract void sendCloseSignal();
}