package app.util;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Holds application-wide configuration constants.
 */
public class Config {
    /** Path to the server JAR file. */
    public static final Path SERVERJAR = Paths.get("server.jar");
    /** Port used by the local server. */
    public static final int LOCALPORT = 7789;
    /** IP address of the online server, or {@code null} if not configured. */
    public static final String ONLINEIP = null;
    /** Port used by the online server. */
    public static final int ONLINEPORT = 7789;
    /** Whether debug mode is enabled. */
    public static final boolean DEBUG = false;

    // swing
    /** Whether the Swing user interface is supported. */
    public static final boolean SWING_SUPPORTED = false;
    /** Background color used by the Swing user interface. */
    public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    /** Foreground color used by the Swing user interface. */
    public static final Color FOREGROUND_COLOR = Color.WHITE;

    // tic tac toe
    // TODO: in own config file
    /** Whether tic-tac-toe displays X and O symbols. */
    public static final boolean X_AND_O = false;
}