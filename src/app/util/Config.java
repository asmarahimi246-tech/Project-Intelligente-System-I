package app.util;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Config
 */
public class Config {

    public static final Path SERVERJAR = Paths.get("server.jar");
    public static final int LOCALPORT = 7789;
    public static final String ONLINEIP = null;
    public static final int ONLINEPORT = 7789;
    public static final boolean DEBUG = true;

    // swing ui
    public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    public static final Color FOREGROUND_COLOR = Color.WHITE;
}
