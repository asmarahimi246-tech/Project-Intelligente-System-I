package app.common.util;

/**
 * very simple
 * Logger
 */
public class Logger {

    /**
     * logs the message when DEBUG is set to true in the config
     * 
     * @param message the message
     */
    public static void logDebug(String message) {
        if (Config.DEBUG) {
            System.out.println(message);
        }
    }
}
