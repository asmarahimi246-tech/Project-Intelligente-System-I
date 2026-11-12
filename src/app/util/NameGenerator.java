package app.util;

import java.util.Random;

/**
 * NameGenerator
 * 
 * Generates a unique name
 */
public class NameGenerator {
    private static final Random RANDOM = new Random();

    /** 
     * Gets a name 
     * 
     * @return a random name
     */
    public String getName() {
        int randomnumber = 1000 + RANDOM.nextInt(9000);
        String playername = "Player" + randomnumber;

        return playername;
    }
}