package app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class AppTest {

    private App app;

    @BeforeAll 
    public void setUp() {
        app = App.getInstance();
    }

    @AfterAll 
    public void tearDown() {
        App.sendCloseSignal();
    }

    @Test
    public void testApp() {
        assertNotNull(app);
    }
}