package app.util;

import java.awt.Color;
import java.awt.Font;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

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
    public static final boolean DEBUG = true;

    // swing
    /** Whether the Swing user interface is supported. */
    public static final boolean SWING_SUPPORTED = true;
    /** Background color used by the Swing user interface. */
    public static final Color BACKGROUND_COLOR = new Color(38, 42, 48);
    /** Foreground color used by the Swing user interface. */
    public static final Color FOREGROUND_COLOR = new Color(235, 238, 242);
    /** Secondary background for cards, panels, and inputs. */
    public static final Color SURFACE_COLOR = new Color(50, 55, 63);
    /** Subtle border and divider color. */
    public static final Color BORDER_COLOR = new Color(75, 82, 92);
    /** Muted text for hints and secondary labels. */
    public static final Color MUTED_FOREGROUND_COLOR = new Color(165, 173, 184);
    /** Soft accent color for links and selected controls. */
    public static final Color ACCENT_COLOR = new Color(143, 180, 210);
    /** Hover state for accent-colored controls. */
    public static final Color ACCENT_HOVER_COLOR = new Color(164, 195, 220);
    /** Soft success color. */
    public static final Color SUCCESS_COLOR = new Color(145, 190, 160);
    /** Soft warning color. */
    public static final Color WARNING_COLOR = new Color(225, 190, 125);
    /** Soft error color. */
    public static final Color ERROR_COLOR = new Color(220, 145, 145);
    /** Default border used by the Swing user interface */
    public static final Border BORDER = BorderFactory.createLineBorder(BORDER_COLOR, 2, true);
    /** Default font for Swing user interface */
    public static final Font MEDIUM_FONT = new Font("Arial", Font.BOLD, 30);
    
    // tic tac toe
    // TODO: in own config file
    /** Whether tic-tac-toe displays X and O symbols. */
    public static final boolean X_AND_O = true;
}