package app.games.TikTakToe.common;

/**
 * Timer
 */
public class Timer {
    /**
     * Closes game in x seconds
     */
    public void closeInSeconds(int seconds) {

        // for testing
        System.out.println("[info] game ends in " + seconds + "s");
        try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
