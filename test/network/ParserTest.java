package network;

import app.network.MessageHandler;
import app.network.ServerMessage;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static app.network.MessageAddon.*;
import static app.network.MessageType.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private String svrMsg = "";
    private final MessageHandler handler = new MessageHandler();

    @Test
    void testSetChallenge() {

        svrMsg = "SVR GAME CHALLENGE {CHALLENGER: \"Sjors\", GAMETYPE: \"Guess Game\", CHALLENGENUMBER: \"1\"}";
       ServerMessage msg = handler.handleMessage(svrMsg);

        assertEquals(CHALLENGE, msg.getAddon());
        assertEquals(Map.of("CHALLENGER","Sjors",
                            "GAMETYPE", "Guess Game",
                            "CHALLENGENUMBER", "1"),
                    msg.getGameDetails());
    }

    @Test
    void testSetCancelled() {
        svrMsg = "SVR GAME CHALLENGE CANCELLED {CHALLENGENUMBER: \"8\"}";

        ServerMessage msg = handler.handleMessage(svrMsg);

        assertEquals(CANCELLED, msg.getAddon());
        assertEquals(Map.of(
                        "CHALLENGENUMBER","8"),
                    msg.getGameDetails());
    }

    @Test
    void testEmptySvrMsg() {
        svrMsg = "";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> handler.handleMessage(svrMsg));

        String expectedMsg = "Cannot parse a null or empty server message";
        String actualMsg = exception.getMessage();

        assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    void testNullSvrMsg() {
        svrMsg = null;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> handler.handleMessage(svrMsg));

        String expectedMsg = "Cannot parse a null or empty server message";
        String actualMsg = exception.getMessage();

        assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    void testParserMsgReset() {
        svrMsg = "SVR GAME YOURTURN {TURNMESSAGE: \"<bericht voor deze beurt>\"}";

        ServerMessage msg = handler.handleMessage(svrMsg);

        assertEquals(YOURTURN, msg.getAddon());
        assertEquals(Map.of("TURNMESSAGE","<bericht voor deze beurt>"),
                msg.getGameDetails());

        svrMsg = "ERR Command not found";
        msg = handler.handleMessage(svrMsg);

        assertEquals(ERR, msg.getType());
        assertEquals("Command not found", msg.getErrorDetails());
        assertNull(msg.getGameDetails());
    }

    @Test
    void testTest() {

    }
}