package games.kasia.app.client.common;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Constants
 */
public class Config {
    public static final boolean DEBUG = true;

    // Server sutff
    public static final Path SERVERJAR = Paths.get("server.jar");
    public static final int LOCALPORT = 7789;
    public static final String ONLINEIP = null;
    public static final int ONLINEPORT = 7789;

    public static final String ADMINPASSWORD = "kaas1234";

}